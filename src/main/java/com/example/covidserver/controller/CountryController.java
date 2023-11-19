package com.example.covidserver.controller;

import com.example.covidserver.DTO.CountryDTO;
import com.example.covidserver.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
@AllArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<List<CountryDTO>> allCountries() {
        return ResponseEntity.ok(countryService.allCountries());
    }
}
