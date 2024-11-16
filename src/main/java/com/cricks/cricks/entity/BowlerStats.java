package com.cricks.cricks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BowlerStats {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer id;
  public Integer bowlerId;
  public Integer matchId;
  public Integer balls;
  public Integer runsGiven;
  public Integer wickets;
}
