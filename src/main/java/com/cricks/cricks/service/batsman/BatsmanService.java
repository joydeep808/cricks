package com.cricks.cricks.service.batsman;



import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cricks.cricks.entity.BatsmanStats;
import com.cricks.cricks.entity.Match.MatchStatus;
import com.cricks.cricks.exception.thrown_exception.batsman.BatsmanStatsNotFound;
import com.cricks.cricks.exception.thrown_exception.match.*;
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

  public ResponseEntity<Response<BatsmanStats>> getBatsmanStats(Integer matchId, Integer batsmanId) throws Exception{
    BatsmanStats foundBatsmanStats = batsmanStatsRepo.findByMatchIdAndBatsmanId(matchId, batsmanId).orElse(null);
    if (foundBatsmanStats == null)
      throw new MatchNotStarted("Batsman stats not found", 404);
    return new Response<BatsmanStats>().sendSuccessResponse("Batsman stats found successfully done!", 200, foundBatsmanStats)
        .sendResponseEntity();
  }

  public ResponseEntity<Response<Page<BatsmanStats>>> getBatsmanStatsByMatchId(Integer matchId , Integer pageNumber) throws Exception{
    Pageable pageable = PageRequest.of(pageNumber <= 0 ? 0 : pageNumber - 1, 11 , Sort.by("createdAt"));
  Page<BatsmanStats> allBatsmanStats = batsmanStatsRepo.findAllBatsmanStats(matchId , pageable);
  if (allBatsmanStats.isEmpty()) {
      throw new MatchNotFound("Match not found ", 404);
  }
  return new Response<Page<BatsmanStats>>().sendSuccessResponse("Successfully found", 200 , allBatsmanStats).sendResponseEntity();
  }


  public ResponseEntity<Response<BatsmanStats>> getBatsmanStatsByMatchIdAndBatsmanId(Integer matchId , Integer batsmanId)throws Exception{
    BatsmanStats foundBatsmanStats = batsmanStatsRepo.findByMatchIdAndBatsmanId(matchId, batsmanId).orElse(null);
    if (foundBatsmanStats == null)
      throw new BatsmanStatsNotFound("Batsman stats not found", 404);
    return new Response<BatsmanStats>().sendSuccessResponse("Batsman stats found successfully done!", 200, foundBatsmanStats)
        .sendResponseEntity();
  }
   
  public ResponseEntity<Response<Page<BatsmanStats>>> getBatsmanStatsByBatsmanId(Integer batsmanId , Integer pageNumber) throws Exception{
    Pageable pageable = PageRequest.of(pageNumber <= 0 ? 0 : pageNumber - 1, 5 , Sort.by("createdAt").descending());
    Page<BatsmanStats> allBatsmanStats = batsmanStatsRepo.findAllBatsmanStatsByBatsmanId(batsmanId , pageable);
    if (allBatsmanStats.isEmpty() && allBatsmanStats.getTotalElements() == 0) {
        throw new BatsmanStatsNotFound("Batsman stats not found", 404);
    }
    return new Response<Page<BatsmanStats>>().sendSuccessResponse("Successfully found", 200 , allBatsmanStats).sendResponseEntity();
    }
}
