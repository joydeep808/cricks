package com.cricks.cricks.dto.match;

import java.util.Optional;

import org.springframework.beans.factory.config.YamlProcessor.MatchStatus;

import lombok.*;


@Data
@Builder
@AllArgsConstructor

public class UpdateMatchDetailsDto {
  private Optional<Integer> id;
  private Optional<Integer> teamA;
  private Optional<Integer> teamB;
  private Optional<Integer> venueId;
  private Optional<Integer> seriesId;
  private Optional<Integer> matchNumber;
  private Optional<MatchStatus> matchStatus;

}
