package com.cricks.cricks.entity;

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
  private Boolean isOut;

  public BatsmanStats(){
    this.ballFaced = 0;
    this.totalBoundries = 0;
    this.totalSixes = 0;
    this.runScored = 0;
    this.isOut = false;
  }
}
