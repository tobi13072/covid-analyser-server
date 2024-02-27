package com.example.covidserver.service;

import com.example.covidserver.DTO.WHORegionDTO;
import com.example.covidserver.TestConfig;
import com.example.covidserver.repository.WHORegionRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class WHORegionServiceTests {
    @Autowired
    private WHORegionService whoRegionService;

    @Autowired
    private WHORegionRepository whoRegionRepository;


    @Test
    public void getAllWhoRegionTest() {
        //given
        int expectedWhoRegionAmount = 6;

        //when
        List<WHORegionDTO> actualWhoRegionAmount = whoRegionService.allWHORegions();

        //then
        assertEquals(expectedWhoRegionAmount, actualWhoRegionAmount.size());
    }
}
