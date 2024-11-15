

package com.cricks.cricks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.Player;


@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
  @Query(value = "SELECT id FROM player WHERE phone = :phone", nativeQuery = true)
  Optional<Integer> isPlayerAlreadyExist(@Param("phone") Integer phone );
}
