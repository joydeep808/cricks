package com.cricks.cricks.service.ball;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cricks.cricks.entity.*;
import com.cricks.cricks.entity.Ball.BoundaryType;
import com.cricks.cricks.exception.thrown_exception.match.MatchNotFound;
import com.cricks.cricks.exception.thrown_exception.over.*;
import com.cricks.cricks.repository.*;
import com.cricks.cricks.service.batsman.BatsmanService;
import com.cricks.cricks.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BallService {
  private final BallRepo ballRepo;
  private final OverRepo overRepo;
  private final BatsmanStatsRepo batsmanStatsRepo;
  private final BowlerStatsRepo bowlerStatsRepo;
  private final BatsmanService batsmanService;

  public ResponseEntity<Response<Ball>> createBall(Ball ball, Integer matchId) throws Exception {
    Boolean isOverFound = overRepo.isOverCompleted(ball.getOverId()).orElse(null);
    ;
    if (isOverFound == null) {
      throw new OverNotFound("Over not found", 404);
    }
    if (isOverFound) {
      throw new OverAlreadyCompleted("Over already completed", 400);
    }

    BatsmanStats foundBatsmanStats = batsmanStatsRepo.findByMatchIdAndBatsmanId(matchId, ball.getBallNumber())
        .orElse(null);
    updateBatsmanStats(ball, foundBatsmanStats, matchId);

    BowlerStats foundBowlerStats = bowlerStatsRepo.findByMatchIdAndBowlerId(matchId, ball.getBallNumber()).orElse(null);
    updateBowlerStats(ball, matchId, foundBowlerStats);
    ballRepo.save(ball);

    return new Response<Ball>().sendSuccessResponse("Ball created successfully done!", 201, ball).sendResponseEntity();
  }

  public void updateBatsmanStats(Ball ball, BatsmanStats foundBatsmanStats, Integer matchId) throws Exception {
    if (foundBatsmanStats == null) {
      BatsmanStats newBatsmanStats = BatsmanStats.builder().ballFaced(1).runScored(ball.getRun())
          .totalBoundries(ball.getBoundaryType().equals(BoundaryType.FOUR) ? 1 : 0)
          .totalSixes(ball.getBoundaryType().equals(BoundaryType.SIX) ? 1 : 0).isOut(ball.getIsWicket())
          .matchId(matchId).build();
      batsmanService.createBatsmanStats(newBatsmanStats);
    }
    BatsmanStats updateBatsmanStats = BatsmanStats.builder().ballFaced(foundBatsmanStats.getBallFaced() + 1)
        .runScored(foundBatsmanStats.getRunScored() + ball.getRun())
        .totalBoundries(
            foundBatsmanStats.getTotalBoundries() + (ball.getBoundaryType().equals(BoundaryType.FOUR) ? 1 : 0))
        .totalSixes(foundBatsmanStats.getTotalSixes() + (ball.getBoundaryType().equals(BoundaryType.SIX) ? 1 : 0))
        .isOut(foundBatsmanStats.getIsOut() || ball.getIsWicket()).build();
    ;

    batsmanStatsRepo.save(updateBatsmanStats);

  }

  public void updateBowlerStats(Ball ball, Integer matchId, BowlerStats bowlerStats) throws Exception {
    if (bowlerStats == null) {
      BowlerStats newBowlerStats = BowlerStats.builder().balls(1).runsGiven(ball.getRun()).matchId(matchId)
          .bowlerId(ball.getBowlerId())
          .wickets(ball.getIsWicket() ? 1 : 0).build();
      bowlerStatsRepo.save(newBowlerStats);
    }

    BowlerStats updateBowlerStats = BowlerStats.builder().balls(bowlerStats.getBalls() + 1)
        .runsGiven(bowlerStats.getRunsGiven() + ball.getRun())
        .wickets(bowlerStats.getWickets() + (ball.getIsWicket() ? 1 : 0)).bowlerId(ball.getBowlerId()).matchId(matchId)
        .build();
    bowlerStatsRepo.save(updateBowlerStats);

  }

  // Get ball service

  public ResponseEntity<Response<Page<List<Ball>>>> getBallRecordsByMatchIdAndInnings(Integer matchId, Integer innings,
      Integer pageNumber) throws Exception {
    Pageable pageRequest = PageRequest.of(pageNumber <= 0 ? 0 : pageNumber - 1, 10, Sort.by("ball_number"));
    Page<List<Ball>> allBallsByMatchAndInnings = ballRepo.getAllBallsByMatchAndInnings(matchId, innings, pageRequest);
    ;
    if (allBallsByMatchAndInnings.isEmpty()) {
      throw new MatchNotFound("Match not found", 404);
    }
    return new Response<Page<List<Ball>>>()
        .sendSuccessResponse("Ball records found successfully", 200, allBallsByMatchAndInnings).sendResponseEntity();
  }


  public ResponseEntity<Response<List<Ball>>> getBallsOfAnOver(Integer matchId , Integer overNumber , Integer inningsNumber) throws Exception{
    List<Ball> allBallsOfAnOver = ballRepo.getBallsOfAnOver(matchId  , overNumber , inningsNumber);
    if (allBallsOfAnOver.isEmpty() && allBallsOfAnOver.size() == 0) {
      throw new OverNotFound("Over not found", 404);
    }
    return new Response<List<Ball>>().sendSuccessResponse("Balls found successfully", 200, allBallsOfAnOver).sendResponseEntity();
        
  }


  

  
}
