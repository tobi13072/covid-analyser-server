package com.example.covidserver.integration;

import com.example.covidserver.DTO.CountryStatDTO;
import com.example.covidserver.TestConfig;
import com.example.covidserver.domain.Continent;
import com.example.covidserver.domain.Country;
import com.example.covidserver.domain.CountryStat;
import com.example.covidserver.domain.WHORegion;
import com.example.covidserver.repository.ContinentRepository;
import com.example.covidserver.repository.CountryRepository;
import com.example.covidserver.repository.CountryStatRepository;
import com.example.covidserver.repository.WHORegionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;

import org.hibernate.stat.Statistics;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestConfig.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
@Transactional
public class StatisticsControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CountryStatRepository countryStatRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private WHORegionRepository whoRegionRepository;
    @Autowired
    private ContinentRepository continentRepository;

    private Country country;

    @Before
    public void setUp() throws Exception {

        country = addCountry();
    }

    @Test
    @WithMockUser(username = "Bobi", roles = "ADMIN")

    public void addValidStatisticsTest() throws Exception{

        CountryStatDTO jsonBody = createCountryStat();
        createCountryStatEntity();
        ObjectMapper objectMapper = new ObjectMapper();

        mvc.perform(post("/stats/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Lists.newArrayList(jsonBody)))).andExpect(status().isOk());

        CountryStat countryStat = countryStatRepository.findById(1).orElse(null);
        Assertions.assertNotNull(countryStat);
        Assertions.assertEquals(1, countryStat.getCountry_population());
        Assertions.assertEquals(1, countryStat.getActive_cases());
        Assertions.assertEquals(1, countryStat.getTotal_deaths());
        Assertions.assertEquals(1, countryStat.getActive_cases());
        Assertions.assertEquals(1, countryStat.getTotal_recovered());

    }

    private void createCountryStatEntity() {
        CountryStat countryStat = CountryStat.builder()
                .stat_id(1)
                .active_cases(0L)
                .country_population(0L)
                .country(country)
                .total_deaths(0)
                .total_cases(0)
                .total_recovered(0)
                .build();

        countryStatRepository.save(countryStat);
    }
    private CountryStatDTO createCountryStat() {
        return CountryStatDTO.builder()
                .stat_id(1)
                .active_cases(1L)
                .country_population(1L)
                .country(country)
                .total_deaths(1)
                .total_cases(1)
                .total_recovered(1)
                .build();
    }
  
    private Country addCountry() {
        WHORegion whoRegion = whoRegionRepository.findWHORegionByName("Europe").get();
        Continent continent = continentRepository.findContinentByName("Europe").get();

        Country country = new Country();
        country.setCountry_id(1);
        country.setCountry_name("Poland");
        country.setWho(whoRegion);
        country.setContinent(continent);

        return countryRepository.save(country);
    }

}
