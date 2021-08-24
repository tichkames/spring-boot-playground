package com.exokames.playground.domain.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
@ToString
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    private String phone;
    private String website;

    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;
}
