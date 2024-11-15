
package com.cricks.cricks.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Data
@Builder
public class Ball {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer Id;
  @NotNull(message = "Please provide the ball number ")
  @Positive(message = "Ball number should be positive")
  private Integer ballNumber;
  @JsonFormat(shape=JsonFormat.Shape.STRING)
  private BallType ballType;
  @Positive
  private Integer run;
 public enum BallType{
  WIDE,W,NB , DELIVERY
  }
  private Integer batsman;

  private Integer overId;
  public Ball(){

    this.run = 0;
    this.ballType = BallType.DELIVERY;
  }


}
