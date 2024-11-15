
package com.cricks.cricks.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Over {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer Id;
  @NotNull(message="Match id is required")
  @Positive(message = "Match id should be positive")
  private Integer matchId;
  @NotNull(message="Over number is required")
  @Positive(message = "Overnumber should be positive")
  private Integer overNumber;
  @NotNull(message="Team id is requird")
  @Positive(message = "Team id should be positive")
  private Integer teamId;
  @NotNull(message="Bowler id is required")
  @Positive(message = "Bowler id should be positive")
  private Integer bowler;
  private Integer totalRuns;
  public Over(){
    this.totalRuns = 0;
  }

}
