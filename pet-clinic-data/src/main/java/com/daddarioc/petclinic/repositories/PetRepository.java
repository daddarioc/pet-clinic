package com.daddarioc.petclinic.repositories;

import com.daddarioc.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
