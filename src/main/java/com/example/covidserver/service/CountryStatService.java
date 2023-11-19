package com.example.covidserver.service;

import com.example.covidserver.DTO.CountryStatDTO;
import com.example.covidserver.domain.CountryStat;
import com.example.covidserver.repository.CountryStatRepository;
import com.example.covidserver.utils.CountryStatDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryStatService {

    private final CountryStatRepository statRepository;

    public List<CountryStatDTO> allStats() {
        return statRepository.findAll()
                .stream()
                .map(CountryStatDTOMapper::map)
                .collect(Collectors.toList());
    }

    public List<CountryStatDTO> findByContinent(String continent) {
        return statRepository.findStatisticsByContinentName(continent)
                .stream()
                .map(CountryStatDTOMapper::map)
                .collect(Collectors.toList());
    }

    public List<CountryStatDTO> findByCountry(String country) {
        return statRepository.findCountryStatByCountryName(country)
                .stream()
                .map(CountryStatDTOMapper::map)
                .collect(Collectors.toList());
    }

    public CountryStatDTO findByCountryStatID(Integer id) {
        return statRepository.findById(id)
                .map(CountryStatDTOMapper::map)
                .orElseThrow(RuntimeException::new);
    }

    public List<CountryStatDTO> findCountryStatByAssessingDeaths(Integer deaths) {
        return statRepository.findCountryStatByAssessingDeaths(deaths)
                .stream()
                .map(CountryStatDTOMapper::map)
                .collect(Collectors.toList());
    }

    public List<CountryStatDTO> findCountryStatByFullStatistics(String continent, Integer deaths) {
        if (Objects.nonNull(continent) && Objects.nonNull(deaths)) {
            return statRepository.findCountryStatByFullStatistics(continent, deaths)
                    .stream()
                    .map(CountryStatDTOMapper::map)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public Boolean updateStatisticsForGivenCountry(List<CountryStatDTO> objectsToObject) {
        objectsToObject.forEach(country -> {
            CountryStat founded = statRepository.findById(country.getStat_id()).orElse(null);
            if (Objects.nonNull(founded)) {
                CountryStatDTO oldData = CountryStatDTOMapper.map(founded);
                CountryStatDTO updated = sumCurrentAndNewDataStatistics(oldData, country);
                CountryStat toSave = CountryStatDTOMapper.mapInverted(updated);
                statRepository.save(toSave);
            }
        });
        return Boolean.TRUE;
    }

    private CountryStatDTO sumCurrentAndNewDataStatistics(CountryStatDTO oldData, CountryStatDTO newData) {
        oldData.setActive_cases(newData.getActive_cases() + oldData.getActive_cases());
        oldData.setTotal_deaths(newData.getTotal_deaths() + oldData.getTotal_deaths());
        oldData.setTotal_recovered(newData.getTotal_recovered() + oldData.getTotal_recovered());
        oldData.setTotal_cases(newData.getTotal_cases() + oldData.getTotal_cases());
        oldData.setCountry_population(newData.getCountry_population() + oldData.getCountry_population());
        return oldData;
    }
}
