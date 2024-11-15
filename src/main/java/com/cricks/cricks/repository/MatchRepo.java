

package com.cricks.cricks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.dto.match.TeamsFromMatchDto;
import com.cricks.cricks.entity.Match;
import com.cricks.cricks.entity.Match.MatchStatus;

@Repository
public interface MatchRepo extends  JpaRepository<Match, Integer> {
  @Query(value = "SELECT id FROM match WHERE series_id = :seriesId AND match_number = :matchNumber", nativeQuery = true)
  Optional<Integer> isMatchAlreadyExist(@Param("seriesId") Integer seriesId , @Param("matchNumber") Integer matchNumber);

  @Query(value = "SELECT team_a , team_b FROM match WHERE id = :matchId", nativeQuery = true)
  Optional<TeamsFromMatchDto> getTeamsFromMatch(@Param("matchId") Integer matchId);

  @Query(value = "SELECT status FROM match WHERE id = :matchId", nativeQuery = true)
  Optional<MatchStatus> getMatchStatus(@Param("matchId") Integer matchId); 
}
