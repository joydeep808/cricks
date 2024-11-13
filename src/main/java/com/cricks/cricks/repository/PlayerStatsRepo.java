

package com.cricks.cricks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.BatsmanStats;

@Repository
public interface PlayerStatsRepo extends JpaRepository<BatsmanStats, Integer> {

}
