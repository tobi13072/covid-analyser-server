package com.example.covidserver.controller;

import com.example.covidserver.DTO.CountryStatDTO;
import com.example.covidserver.service.CountryStatService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
@Transactional(isolation = Isolation.READ_COMMITTED)
@AllArgsConstructor
public class StatisticsController {

    private final CountryStatService statService;

    @GetMapping("/all")
    public ResponseEntity<List<CountryStatDTO>> allStatistics() {
        return ResponseEntity.ok(statService.allStats());
    }

    @GetMapping("/continent/{continent}")
    public ResponseEntity<List<CountryStatDTO>> allStatisticsForContinent(@PathVariable String continent) {
        return ResponseEntity.ok(statService.findByContinent(continent));
    }

    @GetMapping("/country")
    public ResponseEntity<List<CountryStatDTO>> allStatisticsForCountry(@RequestParam(name = "country") String country) {
        return ResponseEntity.ok(statService.findByCountry(country));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryStatDTO> findStatisticsByID(@PathVariable Integer id) {
        return ResponseEntity.ok(statService.findByCountryStatID(id));
    }

    @GetMapping("/deaths/{deaths}")
    public ResponseEntity<List<CountryStatDTO>> findCountryStatByAssessingDeaths(@PathVariable Integer deaths) {
        return ResponseEntity.ok(statService.findCountryStatByAssessingDeaths(deaths));
    }

    @GetMapping("/filter/{continent}/{deaths}")
    public ResponseEntity<List<CountryStatDTO>> findCountryStatByFullStatistics(@PathVariable String continent,
                                                                                @PathVariable Integer deaths) {
        return ResponseEntity.ok(statService.findCountryStatByFullStatistics(continent, deaths));
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateStatistics(@RequestBody List<CountryStatDTO> updatedObjects) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            return ResponseEntity.ok(this.statService.updateStatisticsForGivenCountry(updatedObjects));
        }
        return new ResponseEntity<Boolean>(Boolean.FALSE,HttpStatus.UNAUTHORIZED);
    }
}
