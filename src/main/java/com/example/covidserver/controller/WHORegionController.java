package com.example.covidserver.controller;

import com.example.covidserver.DTO.WHORegionDTO;
import com.example.covidserver.service.WHORegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/who")
@AllArgsConstructor
public class WHORegionController {

    private final WHORegionService regionService;

    @GetMapping("/all")
    public ResponseEntity<List<WHORegionDTO>> getAllWHORegions() {
        return ResponseEntity.ok(regionService.allWHORegions());
    }
}
