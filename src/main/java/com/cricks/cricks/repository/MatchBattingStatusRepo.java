package com.cricks.cricks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.MatchBattingStatus;


@Repository
public interface MatchBattingStatusRepo extends JpaRepository<MatchBattingStatus, Integer> {
  Optional<MatchBattingStatus> findByMatchId(Integer matchId);
  
}
