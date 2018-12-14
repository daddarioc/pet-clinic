package com.daddarioc.petclinic.bootstrap;

import com.daddarioc.petclinic.model.Owner;
import com.daddarioc.petclinic.model.Vet;
import com.daddarioc.petclinic.services.OwnerService;
import com.daddarioc.petclinic.services.VetService;
import com.daddarioc.petclinic.services.map.OwnerServiceMap;
import com.daddarioc.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
/**
 * Non Spring-managed example of how to get startup data in a HashMap implementation
 */
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Chris");
        owner1.setLastName("D'Addario");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner1.setId(2L);
        owner1.setFirstName("Holly");
        owner1.setLastName("D'Addario");

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("John");
        vet1.setLastName("Smith");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet1.setId(2L);
        vet1.setFirstName("Zach");
        vet1.setLastName("Orange");

        vetService.save(vet2);

        System.out.println("Loaded vets...");

    }
}
