
package com.cricks.cricks.entity.ball;

import com.cricks.cricks.entity.ball.Ball.BallType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Entity
@AllArgsConstructor
@Data
@Builder
public class Ball {

  private Integer id;
  private Integer ballNumber;
  @JsonFormat(shape=JsonFormat.Shape.STRING)
  private BallType ballType;
  private Integer run;
  enum BallType{
  WIDE,W,NB , DELIVERY
  }
  private Integer batsman;

  public Ball(){

    this.run = 0;
    this.ballType = BallType.DELIVERY;
  }


}
