package com.example.covidserver.repository;

import com.example.covidserver.domain.WHORegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WHORegionRepository extends JpaRepository<WHORegion, Integer> {
}
