
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
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BallType ballType;
  @Positive
  private Integer run;

  public enum BallType {
    WIDE, W, NB, DELIVERY
  }

  @NotNull(message = "Please provide the batsman id")
  @Positive(message = "Batsman id should be positive")
  private Integer batsman;
  @NotNull(message = "Please provide the over id")
  @Positive(message = "Over id should be positive")
  private Integer overId;
  private Boolean isWicket;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private WicketDetails wicketDetails;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private ExtraRuns extraRuns;

  public enum ExtraRuns{
    BYE, LEG_BYE  , NO_BALL, WIDE , NONE
  }

  public enum WicketDetails {
    BOWLED, CAUGHT, LBW, STUMPED, RUN_OUT, HIT_WICKET , NONE
  }

  public Ball() {

    this.run = 0;
    this.ballType = BallType.DELIVERY;
    this.isWicket = false;
    this.wicketDetails = WicketDetails.NONE;
    this.extraRuns = ExtraRuns.NONE;
  }

}
