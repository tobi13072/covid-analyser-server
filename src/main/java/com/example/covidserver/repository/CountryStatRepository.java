package com.example.covidserver.repository;

import com.example.covidserver.domain.CountryStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryStatRepository extends JpaRepository<CountryStat, Integer> {

    @Query("SELECT cs FROM CountryStat cs" +
            " INNER JOIN Country c ON c.country_id = cs.country.country_id" +
            " INNER JOIN Continent con ON con.continent_id = c.continent.continent_id" +
            " WHERE con.continent_name = ?1")
    List<CountryStat> findStatisticsByContinentName(String continent);

    @Query("SELECT cs FROM CountryStat cs WHERE cs.country.country_name = ?1")
    List<CountryStat> findCountryStatByCountryName(String country);

    @Query("SELECT cs FROM CountryStat cs WHERE  cs.total_deaths < ?1")
    List<CountryStat> findCountryStatByAssessingDeaths(Integer deaths);

    @Query("SELECT cs FROM CountryStat cs" +
            " INNER JOIN Country c ON cs.country.country_id = c.country_id" +
            " INNER JOIN Continent con ON con.continent_id = c.continent.continent_id" +
            " WHERE con.continent_name = ?1 AND cs.total_deaths <= ?2")
    List<CountryStat> findCountryStatByFullStatistics(String continent, Integer deaths);

    @Query("SELECT cs.total_deaths FROM CountryStat cs WHERE cs.country.country_name = ?1")
    Optional<Long> findTotalDeathsForGivenCountry(String country);
}
