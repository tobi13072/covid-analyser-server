package com.example.covidserver.repository;

import com.example.covidserver.domain.WHORegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WHORegionRepository extends JpaRepository<WHORegion, Integer> {
    @Query("SELECT who FROM WHORegion who WHERE who.who_name = ?1")
    Optional<WHORegion> findWHORegionByName(String who);
}
