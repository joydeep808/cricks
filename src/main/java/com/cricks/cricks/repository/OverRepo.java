package com.cricks.cricks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.Over;



@Repository
public interface OverRepo extends JpaRepository<Over, Integer> {

  @Query(value = "SELECT * FROM over WHERE match_id = :match AND over_number = :overNumber" , nativeQuery = true)
  Optional<Over> isOverAlreadyExist(@Param("match") Integer match , @Param("overNumber") Integer overNumber);

  @Query(value = "SELECT is_completed FROM over WHERE id = :over", nativeQuery = true)
  Optional<Boolean> isOverCompleted(@Param("over") Integer over);


}
