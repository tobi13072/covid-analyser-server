package com.example.covidserver.service;

import com.example.covidserver.DTO.CountryDTO;
import com.example.covidserver.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public List<CountryDTO> allCountries() {
        return countryRepository.findAll()
                .stream()
                .map(country -> new CountryDTO(
                        country.getCountry_id(),
                        country.getCountry_name(),
                        country.getContinent(),
                        country.getWho()))
                .collect(Collectors.toList());
    }
}
