package com.cricks.cricks.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cricks.cricks.entity.Ball;
import com.cricks.cricks.service.ball.BallService;
import com.cricks.cricks.util.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ball")
public class BallController {

  private final BallService ballService;

  @PostMapping("/create")
  public ResponseEntity<Response<Ball>> createBall(@RequestBody Ball ball ) throws Exception {
   return  ballService.createBall(ball ,1 );
  }


  @PutMapping("/update")
  public ResponseEntity<Response<Ball>> updateBall(@RequestBody Ball ball ) throws Exception {
    return  ballService.createBall(ball ,1 );
  }

  @GetMapping("/get/all")
  public ResponseEntity<Response<Page<List<Ball>>>> getBallRecordsByMatchIdAndInnings(Integer matchId, Integer innings,
      Integer pageNumber) throws Exception {
    return ballService.getBallRecordsByMatchIdAndInnings(matchId, innings, pageNumber);
  }

  @GetMapping("/get/over")
  public ResponseEntity<Response<List<Ball>>> getBallsOfAnOver(Integer matchId , Integer overNumber , Integer inningsNumber) throws Exception{
    return ballService.getBallsOfAnOver(matchId , overNumber , inningsNumber);
  }

}
