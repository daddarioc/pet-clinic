package com.daddarioc.petclinic.repositories;

import com.daddarioc.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
