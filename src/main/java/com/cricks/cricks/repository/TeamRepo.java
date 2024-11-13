
package com.cricks.cricks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {

}
