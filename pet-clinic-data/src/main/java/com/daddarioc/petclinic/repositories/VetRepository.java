package com.daddarioc.petclinic.repositories;

import com.daddarioc.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
