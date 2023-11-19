package com.example.covidserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "whoregion")
public class WHORegion {

    @Id
    @Column(name = "who_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer who_id;
    @Column(name = "who_name")
    private String who_name;
}
