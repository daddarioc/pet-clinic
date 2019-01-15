package com.daddarioc.petclinic.services.springdatajpa;

import com.daddarioc.petclinic.model.Owner;
import com.daddarioc.petclinic.repositories.OwnerRepository;
import com.daddarioc.petclinic.repositories.PetRepository;
import com.daddarioc.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    final String LAST_NAME = "Jones";
    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = new Owner().builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {

        Owner returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();

        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        Owner jones = ownerSDJpaService.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, jones.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(new Owner().builder().id(2L).lastName("Johnson").build());
        ownerSet.add(new Owner().builder().id(3L).lastName("Mayhew").build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);
        Set<Owner> returnedOwners = ownerSDJpaService.findAll();

        assertNotNull(returnedOwners);
        assertEquals(2, returnedOwners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = ownerSDJpaService.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = ownerSDJpaService.findById(1L);

        assertNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = ownerSDJpaService.save(returnOwner);

        assertNotNull(savedOwner);
        assertEquals(savedOwner.getId(), returnOwner.getId());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnOwner);
        verify(ownerRepository, times(1)).delete(returnOwner);
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(anyLong());
        verify(ownerRepository).deleteById(anyLong());
    }
}