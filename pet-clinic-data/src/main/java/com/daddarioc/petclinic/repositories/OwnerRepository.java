package com.daddarioc.petclinic.repositories;

import com.daddarioc.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
