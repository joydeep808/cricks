package com.cricks.cricks.dto.series;

import java.time.LocalDateTime;

public interface SeriesWiseMatchDto {
  Integer getSeriesId();
  String getSeriesName();
  Integer getMatchId();
  String getTeamNameA();  
  String getTeamNameB();
  LocalDateTime getMatchDate();
  String getVenueName();
  String getMatchStatus();
  String getDiscription();
  String getMatchType();

  
}
