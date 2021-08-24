package com.exokames.playground.domain.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String catchPhrase;
    private String bs;
}
