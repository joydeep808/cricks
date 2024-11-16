

package com.cricks.cricks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.BatsmanStats;

@Repository
public interface BatsmanStatsRepo extends JpaRepository<BatsmanStats, Integer> {

  @Query(value= "SELECT * FROM batsman_stats WHERE match_id = :matchId AND batsman_id = :batsmanId", nativeQuery = true)
  Optional<BatsmanStats> findByMatchIdAndBatsmanId(@Param("matchId") Integer matchId,@Param("batsmanId") Integer batsmanId );

}
