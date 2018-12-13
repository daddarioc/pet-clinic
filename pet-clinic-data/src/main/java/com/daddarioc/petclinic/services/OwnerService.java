package com.daddarioc.petclinic.services;

import com.daddarioc.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);
}
