package com.daddarioc.petclinic.repositories;

import com.daddarioc.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
