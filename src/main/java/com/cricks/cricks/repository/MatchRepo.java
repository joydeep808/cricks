

package com.cricks.cricks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.Match;

@Repository
public interface MatchRepo extends  JpaRepository<Match, Integer> {

}
