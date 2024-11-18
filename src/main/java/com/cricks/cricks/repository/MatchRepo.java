
package com.cricks.cricks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.dto.match.TeamsFromMatchDto;
import com.cricks.cricks.entity.Match;
import com.cricks.cricks.entity.Match.MatchStatus;
import com.cricks.cricks.mapper.match.GetMatchInfoMapper;

@Repository
public interface MatchRepo extends JpaRepository<Match, Integer> {
  @Query(value = "SELECT id FROM match WHERE series_id = :seriesId AND match_number = :matchNumber", nativeQuery = true)
  Optional<Integer> isMatchAlreadyExist(@Param("seriesId") Integer seriesId, @Param("matchNumber") Integer matchNumber);

  @Query(value = "SELECT team_a , team_b FROM match WHERE id = :matchId", nativeQuery = true)
  Optional<TeamsFromMatchDto> getTeamsFromMatch(@Param("matchId") Integer matchId);

  @Query(value = "SELECT status FROM match WHERE id = :matchId", nativeQuery = true)
  Optional<MatchStatus> getMatchStatus(@Param("matchId") Integer matchId);

  @Query(value = "SELECT m.id as matchId , m.match_number, t1.team_name as teamNameA , t2.team_name as teamNameB , m.match_status , m.match_date , m.match_type , v.venue_name  , m.description , v.city, c.country FROM match as m JOIN team as t1 ON t1.id = m.team_a JOIN team as t2 ON t2.id = m.team_b JOIN venue as v ON v.id = m.venue_id JOIN country as c ON c.id = v.country_id WHERE m.id = :id", nativeQuery = true)
  Optional<GetMatchInfoMapper> getMatchInfo(@Param("id") Integer id);
}
