package com.cricks.cricks.mapper.series;

import java.time.LocalDateTime;


public interface  SeriesWiseMatchsDetailsMapper {
  Integer getSeriesId();
  Integer getMatchId();
  Integer getTeamA();
  Integer getTeamB();
  LocalDateTime getMatchDate();
  Integer getVenueId();
  String getMatchStatus();
    
}
