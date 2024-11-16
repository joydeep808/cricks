

package com.cricks.cricks.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.Ball;

@Repository
public interface BallRepo extends JpaRepository<Ball, Object> {

  @Query(value =  "SELECT b.* FROM ball as B JOIN over AS o ON b.id = o.over_id WHERE o.match_id = :matchId AND o.innings = :innings", nativeQuery = true)
  Page<List<Ball>> getAllBallsByMatchAndInnings( @Param("matchId") Integer matchId ,@Param("innings") Integer integer , Pageable pageable);
}
