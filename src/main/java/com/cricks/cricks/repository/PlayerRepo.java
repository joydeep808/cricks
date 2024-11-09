

package com.cricks.cricks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.player.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {

}
