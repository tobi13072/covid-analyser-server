package com.example.covidserver.service;

import com.example.covidserver.DTO.ContinentDTO;
import com.example.covidserver.DTO.WHORegionDTO;
import com.example.covidserver.TestConfig;
import com.example.covidserver.repository.ContinentRepository;
import com.example.covidserver.repository.WHORegionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class ContinentServiceTests {
    @Autowired
    private ContinentService continentService;
    @Test
    public void getAllWhoRegionTest() {
        //given
        int expectedContinentCount = 6;

        //when
        List<ContinentDTO> actualContinentListSize = continentService.allContinents();

        //then
        assertEquals(expectedContinentCount, actualContinentListSize.size());
    }
}
