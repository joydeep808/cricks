package com.cricks.cricks.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class BatsmanStats {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  @NotNull(message="Player id is required")
  private Integer batsmanId;
  @NotNull(message="Match id is required")
  private Integer matchId;
  
  private Integer ballFaced;
  private Integer runScored;
  private Integer totalBoundries;
  private Integer totalSixes;
  private Integer outBy;
  private Boolean isOut;
  private BatsmanStatus status;

  private OutType outType;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  
  private LocalDate  createdAt;


  public enum BatsmanStatus{
    NOT_OUT,
    OUT,
    NOT_BATTED // Player not get the chance to bat 

  }

  public enum OutType{
    RUN_OUT,
    CAUGHT,
    BOWLED,
    LBW,
    STUMPED,
    NONE
  }
  public BatsmanStats(){
    this.status = BatsmanStatus.NOT_BATTED;
    this.ballFaced = 0;
    this.createdAt = LocalDate.now();
    this.totalBoundries = 0;
    this.totalSixes = 0;
    this.runScored = 0;
    this.isOut = false;
  }
}
