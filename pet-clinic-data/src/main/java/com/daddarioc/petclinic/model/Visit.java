package com.daddarioc.petclinic.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    @ManyToOne  // a visit will have a single pet
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
