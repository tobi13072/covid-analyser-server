package com.example.covidserver.service;

import com.example.covidserver.DTO.CountryDTO;
import com.example.covidserver.TestConfig;
import com.example.covidserver.domain.Continent;
import com.example.covidserver.domain.Country;
import com.example.covidserver.domain.WHORegion;
import com.example.covidserver.repository.ContinentRepository;
import com.example.covidserver.repository.CountryRepository;
import com.example.covidserver.repository.WHORegionRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class CountryServiceTests {
    @Autowired
    private CountryService countryService;
    @Autowired
    private WHORegionRepository whoRegionRepository;
    @Autowired
    private ContinentRepository continentRepository;
    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void getAllCountriesTest() {
        //Given
        long expectedCountiesCount = 1;
        addCountry();

        //When
        List<CountryDTO> expectedCountiresListCount = countryService.allCountries();
        CountryDTO countryDTO = expectedCountiresListCount.stream().findAny().orElse(null);

        //Then
        Assertions.assertEquals(expectedCountiesCount, expectedCountiresListCount.size());
        Assertions.assertNotNull(countryDTO);
        Assertions.assertEquals("Poland", countryDTO.getCountry_name());
    }

    private void addCountry() {
        WHORegion whoRegion = whoRegionRepository.findWHORegionByName("Europe").get();
        Continent continent = continentRepository.findContinentByName("Europe").get();

        Country country = new Country();
        country.setCountry_name("Poland");
        country.setWho(whoRegion);
        country.setContinent(continent);

        countryRepository.save(country);
    }
}
