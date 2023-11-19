package com.example.covidserver;

import com.example.covidserver.service.ContinentService;
import com.example.covidserver.service.UserService;
import com.example.covidserver.domain.dictionary.Role;
import com.example.covidserver.service.WHORegionService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommandRunner implements CommandLineRunner {
    private static final String CONTINENT_EUROPE = "Europe";
    private static final String CONTINENT_NORTH_AMERICA = "North-America";
    private static final String CONTINENT_SOUTH_AMERICA = "South-America";
    private static final String CONTINENT_ASIA = "Asia";
    private static final String CONTINENT_AFRICA = "Africa";
    private static final String CONTINENT_AUSTRALIA_OCEANIA = "Australia-Oceania";

    private static final String WHO_AMERICAS = "Americas";
    private static final String WHO_SOUTH_EAST_ASIA = "South-EastAsia";
    private static final String WHO_EUROPE = "Europe";
    private static final String WHO_AFRICA = "Africa";
    private static final String WHO_MEDITERRANEAN = "EasternMediterranean";
    private static final String WHO_WESTERN_PACIFIC = "WesternPacific";


    private static final String USER_USERNAME = "john";
    private static final String USER_PASSWORD = "user";
    private static final String ADMIN_USERNAME = "mario";
    private static final String ADMIN_PASSWORD = "admin";

    private final UserService userService;
    private final ContinentService continentService;
    private final WHORegionService whoRegionService;

    @Override
    public void run(String... args) throws Exception {

        //Admin user creation if not exists
        userService.saveNewUser(ADMIN_USERNAME, ADMIN_PASSWORD, Role.ADMIN);
        //Plain user creation if not exists
        userService.saveNewUser(USER_USERNAME, USER_PASSWORD, Role.USER);

        //Fill database with continents
        continentService.saveNewContinentIfNotExists(CONTINENT_EUROPE);
        continentService.saveNewContinentIfNotExists(CONTINENT_ASIA);
        continentService.saveNewContinentIfNotExists(CONTINENT_AFRICA);
        continentService.saveNewContinentIfNotExists(CONTINENT_SOUTH_AMERICA);
        continentService.saveNewContinentIfNotExists(CONTINENT_NORTH_AMERICA);
        continentService.saveNewContinentIfNotExists(CONTINENT_AUSTRALIA_OCEANIA);
        //Fill database with WHO Regions
        whoRegionService.saveWHORegionIfNotExists(WHO_AMERICAS);
        whoRegionService.saveWHORegionIfNotExists(WHO_AFRICA);
        whoRegionService.saveWHORegionIfNotExists(WHO_EUROPE);
        whoRegionService.saveWHORegionIfNotExists(WHO_SOUTH_EAST_ASIA);
        whoRegionService.saveWHORegionIfNotExists(WHO_MEDITERRANEAN);
        whoRegionService.saveWHORegionIfNotExists(WHO_WESTERN_PACIFIC);
    }
}
