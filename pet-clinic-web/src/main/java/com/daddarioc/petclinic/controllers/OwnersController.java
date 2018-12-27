package com.daddarioc.petclinic.controllers;

import com.daddarioc.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnersController {

    private final OwnerService ownerService;

    public OwnersController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/", "/index", "/index.html"})
    public String listOwners(Model model) {

        // add the attribute "owners" to the model, which represents all the owners from the service
        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }
}
