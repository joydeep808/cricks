package com.cricks.cricks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.series.Series;

@Repository
public interface SeriesRepo extends JpaRepository<Series, Integer> {

}
