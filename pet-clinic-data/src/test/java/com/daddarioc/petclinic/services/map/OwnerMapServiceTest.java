package com.daddarioc.petclinic.services.map;

import com.daddarioc.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final String lastName = "Jones";

    @BeforeEach
    void setUp() {

        // mimics dependency injection for our hashmap implementation
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        Owner owner = Owner.builder().id(ownerId).lastName(lastName).build();
        ownerMapService.save(owner);
    }

    @Test
    void findByLastName() {
        Owner jones = ownerMapService.findByLastName(lastName);

        assertNotNull(jones);
        assertEquals(jones.getId(), ownerId);
    }

    @Test
    void findByLastNameNotFound() {
        Owner jones = ownerMapService.findByLastName("asfd");
        assertNull(jones);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(savedOwner.getId(), id);
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {

        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {

        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }
}