package com.daddarioc.petclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a vet in the database
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"specialties"})
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialties",
               joinColumns = @JoinColumn(name = "vet_id"),
               inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    @Column(name = "specialties")
    private Set<Specialty> specialties = new HashSet<>();

}
