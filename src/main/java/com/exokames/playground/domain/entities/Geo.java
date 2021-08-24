package com.exokames.playground.domain.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Geo {
    @Id
    @GeneratedValue
    private Long id;
    private String lat;
    private String lng;
}
