

package com.cricks.cricks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.ball.Ball;

@Repository
public interface BallRepo extends JpaRepository<Ball, Object> {

}
