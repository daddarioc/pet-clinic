package com.daddarioc.petclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a pet in the database
 */
@Data
@EqualsAndHashCode(exclude = {"owner", "petType"})
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")  // indicates in the db there will be an owner_id column
    private Owner owner;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet") // a pet may have many visits
    private Set<Visit> visits = new HashSet<>();

}
