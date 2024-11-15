
package com.cricks.cricks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.Team;


@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {
  @Query(value = "SELECT team_name FROM team where id = :id", nativeQuery = true)
  Optional<String> isTeamExist(@Param("id") Integer id);
}
