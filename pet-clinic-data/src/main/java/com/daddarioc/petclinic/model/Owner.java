package com.daddarioc.petclinic.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a pet owner in the database
 */
@Data
@Entity
@Table(name = "owners")
public class Owner extends Person {

    // indicates there may be many pets for a given owner, cascasde ensures if an owner is deleted, so are the child pets
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

}
