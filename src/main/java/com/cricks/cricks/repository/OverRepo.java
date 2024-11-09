
package com.cricks.cricks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.over.Over;


@Repository
public interface OverRepo extends JpaRepository<Over, Integer> {

}
