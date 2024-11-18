

package com.cricks.cricks.repository;

import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.BatsmanStats;

@Repository
public interface BatsmanStatsRepo extends JpaRepository<BatsmanStats, Integer> {

  @Query(value= "SELECT * FROM batsman_stats WHERE match_id = :matchId AND batsman_id = :batsmanId", nativeQuery = true)
  Optional<BatsmanStats> findByMatchIdAndBatsmanId(@Param("matchId") Integer matchId,@Param("batsmanId") Integer batsmanId );


  @Query(value = "SELECT * FROM batsman_stats WHERE match_id = :matchId", nativeQuery = true)
  Page<BatsmanStats> findAllBatsmanStats(@Param("matchId") Integer matchId , Pageable pageable);


  @Query(value = "SELECT * FROM batsman_stats WHERE player_id = :playerId ", nativeQuery = true)
  Page<BatsmanStats> findAllBatsmanStatsByBatsmanId(@Param("playerId") Integer playerId , Pageable pageable); 
}
