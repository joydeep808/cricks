package com.cricks.cricks.service.websocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.cricks.cricks.entity.Ball;
import com.cricks.cricks.entity.BatsmanStats;
import com.cricks.cricks.entity.BowlerStats;
import com.cricks.cricks.entity.MatchBattingStatus;
import com.cricks.cricks.entity.MatchEvent;
import com.cricks.cricks.entity.PlayingBatsman;
import com.cricks.cricks.repository.BallRepo;
import com.cricks.cricks.repository.BatsmanStatsRepo;
import com.cricks.cricks.repository.BowlerStatsRepo;
import com.cricks.cricks.repository.MatchBattingStatusRepo;
import com.cricks.cricks.repository.MatchEventRepo;
import com.cricks.cricks.repository.MatchRepo;
import com.cricks.cricks.repository.PlayingBatsmanRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchWebSocketService {
  private final BatsmanStatsRepo batsmanStatsRepo;
  private final MatchRepo matchRepo;
  private final BowlerStatsRepo bowlerStatsRepo;
  private final BallRepo ballRepo;
  private final MatchBattingStatusRepo matchBattingStatusRepo;
  private final PlayingBatsmanRepo playingBatsmanRepo;
  private final MatchEventRepo matchEventRepo;
  private final SimpMessagingTemplate template;


  public void sendMatchDetails(Integer matchId , Ball ball)  {

    MatchBattingStatus matchBattingStatus = matchBattingStatusRepo.findByMatchId(matchId).orElse(null);

    // After getting the match details 
    PlayingBatsman playingBatsman = playingBatsmanRepo.findPlayingBatsman(matchBattingStatus.getMatchId() , matchBattingStatus.getBattingTeam());

    Optional<BatsmanStats> byMatchIdAndBatsmanId = batsmanStatsRepo.findByMatchIdAndBatsmanId(playingBatsman.getMatchId(),playingBatsman.getBatsmanIdA());
    Optional<BatsmanStats> byMatchIdAndBatsmanId2 = batsmanStatsRepo.findByMatchIdAndBatsmanId(playingBatsman.getMatchId(),playingBatsman.getBatsmanIdB());;
    Optional<BowlerStats> byMatchIdAndBowlerId = bowlerStatsRepo.findByMatchIdAndBowlerId(playingBatsman.getMatchId(),ball.getBowlerId());;
    MatchEvent matchEvent = matchEventRepo.findByMatchId(playingBatsman.getMatchId());;
    if(byMatchIdAndBatsmanId.isPresent() && byMatchIdAndBatsmanId2.isPresent() && byMatchIdAndBowlerId.isPresent()) {
      Map<String, Object> details = new HashMap<String , Object>();

      BatsmanStats batsmanStats = byMatchIdAndBatsmanId.get();
      BatsmanStats batsmanStats2 = byMatchIdAndBatsmanId2.get();
      BowlerStats bowlerStats = byMatchIdAndBowlerId.get();
      
      details.put("batsmanStats", batsmanStats);
      details.put("batsmanStats2", batsmanStats2);
      details.put("bowlerStats", bowlerStats);
      details.put("event_type", ball.getIsWicket() ? "wicket" : "ball");
      details.put("match_id", playingBatsman.getMatchId());
      details.put("wicket_details", ball.getIsWicket() ? ball.getWicketDetails() : null);
      details.put("batting_team", playingBatsman.getTeamId());
      details.put("score", matchEvent);

      template.convertAndSend("/topic/match", details);
    }








  }
  

  

  
}
