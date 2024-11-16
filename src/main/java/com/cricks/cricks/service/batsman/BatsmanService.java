package com.cricks.cricks.service.batsman;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cricks.cricks.entity.BatsmanStats;
import com.cricks.cricks.entity.Match.MatchStatus;
import com.cricks.cricks.exception.thrown_exception.match.MatchNotStarted;
import com.cricks.cricks.repository.BatsmanStatsRepo;
import com.cricks.cricks.repository.MatchRepo;
import com.cricks.cricks.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BatsmanService {
  
  private final BatsmanStatsRepo batsmanStatsRepo;
  private final MatchRepo matchRepo;

  public ResponseEntity<Response<String>> createBatsmanStats(BatsmanStats batsmanStats) throws Exception{
    MatchStatus foundMatch = matchRepo.getMatchStatus(batsmanStats.getMatchId()).orElse(null);;
    if (!foundMatch.equals(MatchStatus.INPROGRESS)) {
      throw new MatchNotStarted("Match is not in " + foundMatch + " state", 400);
    }
    batsmanStatsRepo.save(batsmanStats);
    return new Response<String>().sendSuccessResponse("Batsman stats created successfully done!", 200).sendResponseEntity();
  }

  

  public Boolean updateBatsmanStats(BatsmanStats batsmanStats){
    batsmanStatsRepo.findByMatchIdAndBatsmanId(batsmanStats.getMatchId(),batsmanStats.getBatsmanId()).ifPresent(batsmanStatsRepo::save);
    return true;
  }
  
}
