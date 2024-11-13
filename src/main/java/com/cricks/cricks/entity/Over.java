
package com.cricks.cricks.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Over {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
  @NotNull(message="Match id is required")

  private Integer matchId;
  @NotNull(message="Over number is required")

  private Integer overNumber;
  @NotNull(message="Team id is requird")
  private Integer teamId;

  @NotNull(message="Bowler id is required")
  private Integer bowler;
  private Integer totalRuns;
  public Over(){
    this.totalRuns = 0;
  }

}
