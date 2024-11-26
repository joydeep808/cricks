
package com.cricks.cricks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.MatchEvent;

@Repository
public interface MatchEventRepo extends JpaRepository<MatchEvent, Integer> {

  MatchEvent findByMatchId(Integer matchId);

  Optional<MatchEvent> findByMatchIdAndTeam(Integer matchId, Integer teamId);

}
