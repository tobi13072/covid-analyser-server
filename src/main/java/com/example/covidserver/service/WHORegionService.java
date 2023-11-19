package com.example.covidserver.service;

import com.example.covidserver.repository.WHORegionRepository;
import com.example.covidserver.DTO.WHORegionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
