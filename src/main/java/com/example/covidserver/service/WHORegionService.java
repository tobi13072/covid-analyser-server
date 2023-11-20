package com.example.covidserver.service;

import com.example.covidserver.DTO.WHORegionDTO;
import com.example.covidserver.domain.WHORegion;
import com.example.covidserver.repository.WHORegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WHORegionService {

    private final WHORegionRepository regionRepository;

    public List<WHORegionDTO> allWHORegions() {
        return regionRepository.findAll()
                .stream()
                .map(who -> new WHORegionDTO(who.getWho_id(), who.getWho_name()))
                .collect(Collectors.toList());
    }

    public void saveWHORegionIfNotExists(String who) {
        WHORegion founded = regionRepository.findWHORegionByName(who).orElse(null);
        if (Objects.isNull(founded)) {
            WHORegion newWHO = new WHORegion();
            newWHO.setWho_name(who);
            regionRepository.save(newWHO);
        }
    }
}
