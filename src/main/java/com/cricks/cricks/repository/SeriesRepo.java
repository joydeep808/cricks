package com.cricks.cricks.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.Series;
import com.cricks.cricks.mapper.series.SeriesWiseMatchsDetailsMapper;


@Repository
public interface SeriesRepo extends JpaRepository<Series, Integer> {
  @Query(value = "SELECT * FROM series WHERE date BETWEEN :start_date AND :end_date", nativeQuery = true)
  Optional<List<Series>> getSeriesByDateRange(@Param("start_date") LocalDate start_date, 
                                               @Param("end_date") LocalDate end_date);
  
  @Query(value = " SELECT s.id as seriesId , m.id AS matchId , t1.team_name as teamA  , t2.team_name AS teamB , m.match_date , m.status as matchStatus FROM series as s JOIN match as m ON m.id = s.id JOIN team as t1 ON t1.id = m.team_a JOIN team as t2 ON t2.id = m.team_b WHERE s.id = :series_id ", nativeQuery = true)
  Optional<List<SeriesWiseMatchsDetailsMapper>> findTheMatchesWithTheSeries(@Param("series_id") Integer series_id);
}
