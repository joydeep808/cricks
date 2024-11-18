package com.cricks.cricks.mapper.match;

public interface GetMatchInfoMapper {
  Integer getMatchId();
  Integer getMatchNumber();
  Integer getTeamNameA();
  Integer getTeamNameB();
  String  getMatchStatus();
  String  getMatchType();
  String  getMatchDate();
  String  getVenueName();
  String  getDiscription();
  String  getCity();
  String  getCountry();


}
