package com.cricks.cricks.service.ball;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cricks.cricks.config.security.AuthenticationAnotation;
import com.cricks.cricks.entity.Ball;
import com.cricks.cricks.repository.BallRepo;
import com.cricks.cricks.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  
public class BallService {
  private final BallRepo ballRepo;
  public ResponseEntity<Response<Ball>> createBall(Ball ball) throws Exception{
    ballRepo.save(ball);
    return new Response<Ball>().sendSuccessResponse("Ball created successfully done!", 201 , ball).sendResponseEntity();
  }
}
