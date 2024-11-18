package com.cricks.cricks.dto.player;


import java.util.Optional;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerUpdateDto {
  @NotNull(message = "Player id is required")
  @Positive(message = "Player id should be positive")
  private Integer id;
  private Optional<String> name;
  private Optional<Integer> phone;
  private Optional<String> profileUrl;
  private Optional<Integer> teamId;
  private Optional<Integer> ranking;
  private Optional<String> role; 
}
