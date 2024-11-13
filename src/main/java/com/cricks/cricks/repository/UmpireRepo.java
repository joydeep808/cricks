
package com.cricks.cricks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.Umpire;

@Repository
public interface UmpireRepo extends JpaRepository<Umpire, Integer>{

}
