package com.cricks.cricks.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@Builder
public class BowlerStats {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @NotNull(message = "Bowler id is required")
  @Positive
  private Integer bowlerId;
  @NotNull(message = "Match id is required")
  @Positive(message = "Match id should be positive")
  private Integer matchId;
  private Integer balls;
  private Integer runsGiven;
  private Integer wickets;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private LocalDate createdAt;


  public BowlerStats(){
    this.createdAt = LocalDate.now();
  }
  

}
