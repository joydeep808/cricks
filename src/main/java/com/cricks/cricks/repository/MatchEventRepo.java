
package com.cricks.cricks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.matchevent.MatchEvent;

@Repository
public interface MatchEventRepo extends JpaRepository<MatchEvent, Integer> {

}
