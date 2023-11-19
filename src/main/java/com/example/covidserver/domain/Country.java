package com.example.covidserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer country_id;
    @Column(name = "country_name")
    private String country_name;
    @ManyToOne
    @JoinColumn(name = "continent_id", nullable = true)
    @JsonBackReference("continent-reference")
    private Continent continent;
    @ManyToOne
    @JoinColumn(name = "who_id", nullable = true)
    @JsonBackReference("who-reference")
    private WHORegion who;
}
