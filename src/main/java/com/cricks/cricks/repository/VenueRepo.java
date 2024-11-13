package com.cricks.cricks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cricks.cricks.entity.Venue;

@Repository
public interface VenueRepo extends JpaRepository<Venue, Integer> {

}
