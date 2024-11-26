package com.cricks.cricks.mapper.match;


public interface MatchStatsMapper {
  Integer getMatchId();
  String getPlayerNameA();
  String getBattingTeam();
  Integer getPlayerScoreA();
  String getPlayerNameB();
  Integer getPlayerScoreB();
  Integer getScore();
  Integer getWickets();
  String getBowlerName();
  Integer getBowlerWickets();
  Integer getBowlerTotalBalls();
}


// Whenever a user comes to our platform then initially 
// fetch all the details like match and series and the current status of the match 
// if everything fetched than i am suppose to subscribe some websockets 
// in this websockets i am suppose to send the details like
    // who is actually playing and score and bowler details batsman details 
/**
 * 
 * {
  "event_type": "wicket",
  "match_id": "12345",
  "wicket_details": {
    "player_out": "Player 2",
    "wicket_type": "caught",
    "bowler": "Bowler 2"
  },
  "batting_team": "Team A",
  "batters": [
    {"name": "Player 1", "runs": 45, "balls": 31},
    {"name": "Player 3", "runs": 0, "balls": 0}
  ],
  "score": {
    "runs": 151,
    "wickets": 4,
    "overs": 15.5
  }
}

 */