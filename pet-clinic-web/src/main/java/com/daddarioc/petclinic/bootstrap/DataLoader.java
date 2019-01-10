package com.daddarioc.petclinic.bootstrap;

import com.daddarioc.petclinic.model.*;
import com.daddarioc.petclinic.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Generate test data when using H2 database
 */
@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(VetService vetService, OwnerService ownerService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    /**
     * Check to see if we should load test data depending on database present
     * @param args Args
     * @throws Exception Exception
     */
    @Override
    public void run(String... args) throws Exception {

        // 12/31/18 do a check to load data only if it isn't yet present when we switch to a real DB
        log.debug("Checking services to see if we should load test data...");
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }

    }

    /**
     * Populate repositories with test data
     */
    private void loadData() {

        log.debug("Loading PetTypes");
        // persist the petType to the map, in order to get the corresponding map id
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(dog);

        log.debug("Loading Specialties");
        // persist the specialties to the map in order to get the corresponding map id
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        radiology.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        radiology.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Chris");
        owner1.setLastName("D'Addario");
        owner1.setAddress("724 Evergreen Terrace");
        owner1.setTelephone("555-867-5309");

        Pet chrisPet = new Pet();
        chrisPet.setPetType(savedDogPetType);
        chrisPet.setOwner(owner1);
        chrisPet.setBirthDate(LocalDateTime.now());
        chrisPet.setName("Scout");

        owner1.getPets().add(chrisPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Holly");
        owner2.setLastName("D'Addario");
        owner2.setAddress("724 Evergreen Terrace");
        owner2.setTelephone("555-867-5309");

        Pet hollyPet = new Pet();
        hollyPet.setPetType(savedCatPetType);
        hollyPet.setOwner(owner2);
        hollyPet.setBirthDate(LocalDateTime.now());
        hollyPet.setName("Quinn");

        owner2.getPets().add(hollyPet);

        ownerService.save(owner2);

        log.debug("Loaded owners");

        Visit catVisit = new Visit();
        catVisit.setPet(hollyPet);
        catVisit.setDate(LocalDateTime.now());
        catVisit.setDescription("Routine checkup");
        visitService.save(catVisit);

        log.debug("Loaded a visit");

        Vet vet1 = new Vet();
        vet1.setFirstName("John");
        vet1.setLastName("Smith");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Zach");
        vet2.setLastName("Orange");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        log.debug("Loaded Vets");
    }
}
