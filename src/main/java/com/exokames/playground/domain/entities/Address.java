package com.exokames.playground.domain.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String suite;
    private String city;
    private String zipcode;

    @OneToOne(cascade = CascadeType.ALL)
    private Geo geo;
}
