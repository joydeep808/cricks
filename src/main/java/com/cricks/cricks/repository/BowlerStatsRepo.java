package com.cricks.cricks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.BowlerStats;

@Repository
public interface BowlerStatsRepo extends JpaRepository<BowlerStats , Integer>{

  @Query(value = "SELECT * FROM bowler_stats WHERE match_id = :matchId AND bowler_id = :bowlerId", nativeQuery = true)
  Optional<BowlerStats> findByMatchIdAndBowlerId(@Param("matchId") Integer matchId , @Param("bowlerId") Integer bowlerId);
}
