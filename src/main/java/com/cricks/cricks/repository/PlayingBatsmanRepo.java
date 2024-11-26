package com.cricks.cricks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.PlayingBatsman;
@Repository
public interface PlayingBatsmanRepo  extends JpaRepository<PlayingBatsman ,Integer>{
  // PlayingBatsman findByMatchIdAnd(Integer matchId);
  @Query(value = "SLECT * from playing_batsman where match_id = :matchId AND team_id = :teamId", nativeQuery = true)
  PlayingBatsman findPlayingBatsman(@Param("matchId") Integer matchId, @Param("teamId") Integer teamId);
}
