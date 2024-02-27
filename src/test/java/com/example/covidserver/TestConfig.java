package com.example.covidserver;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootConfiguration
@EnableJpaRepositories({"com.example.covidserver.repository"})
@ComponentScan(value = {"com.example.covidserver"})
@AutoConfigureDataJpa
@EntityScan({"com.example.covidserver.domain"})
public class TestConfig {
}
