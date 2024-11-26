package com.cricks.cricks.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@Builder
public class MatchEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotNull(message = "Match id is required")
  private Integer matchId;
  @NotNull(message = "Team id is required")
  private Integer teamId;

  private Integer total_boundaries;
  private Integer total_sixes;
  private Integer total_runs;
  private Integer totalWickets;

  public MatchEvent() {
  this.total_boundaries = 0;
  this.total_runs = 0;
  this.total_sixes = 0;
  this.totalWickets = 0;
  }

}
