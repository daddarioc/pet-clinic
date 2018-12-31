package com.daddarioc.petclinic.bootstrap;

import com.daddarioc.petclinic.model.Owner;
import com.daddarioc.petclinic.model.Pet;
import com.daddarioc.petclinic.model.PetType;
import com.daddarioc.petclinic.model.Vet;
import com.daddarioc.petclinic.services.OwnerService;
import com.daddarioc.petclinic.services.PetTypeService;
import com.daddarioc.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Non Spring-managed example of how to get startup data in a HashMap implementation
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(dog);

        System.out.println("Loaded PetTypes...");

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

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("John");
        vet1.setLastName("Smith");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Zach");
        vet2.setLastName("Orange");

        vetService.save(vet2);

        System.out.println("Loaded vets...");

    }
}
