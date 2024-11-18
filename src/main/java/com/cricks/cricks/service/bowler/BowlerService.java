package com.cricks.cricks.service.bowler;

import org.springframework.stereotype.Service;

import com.cricks.cricks.entity.BowlerStats;
import com.cricks.cricks.repository.BowlerStatsRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BowlerService {
  
  private final BowlerStatsRepo bowlerStatsRepo;

  public void createBowlerStats(BowlerStats bowlerStats) {
    bowlerStatsRepo.save(bowlerStats);
  }
}
