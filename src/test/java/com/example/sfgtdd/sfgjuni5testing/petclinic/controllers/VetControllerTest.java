package com.example.sfgtdd.sfgjuni5testing.petclinic.controllers;

import com.example.sfgtdd.sfgjuni5testing.petclinic.fauxspring.Model;
import com.example.sfgtdd.sfgjuni5testing.petclinic.fauxspring.impl.ModelImpl;
import com.example.sfgtdd.sfgjuni5testing.petclinic.model.Speciality;
import com.example.sfgtdd.sfgjuni5testing.petclinic.model.Vet;
import com.example.sfgtdd.sfgjuni5testing.petclinic.services.VetService;
import com.example.sfgtdd.sfgjuni5testing.petclinic.services.map.SpecialityMapService;
import com.example.sfgtdd.sfgjuni5testing.petclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Tag("controllers")
class VetControllerTest {

    private VetController vetController;
    private VetService vetService;

    @BeforeEach
    void setUp() {
        vetService = new VetMapService(new SpecialityMapService());
        vetController = new VetController(vetService);
    }

    @Test
    void listVets() {
        Set<Speciality> specialities = new HashSet<>();

        specialities.add(new Speciality(1L, "Test Description"));

        //given
        Vet vet1 = new Vet(1L, "John", "Doe", specialities);
        Vet vet2 = new Vet(2L, "Jack", "Johnson", specialities);
        vetService.save(vet1);
        vetService.save(vet2);

        Model model = new ModelImpl();

        //when
        String viewName = vetController.listVets(model);

        //then
        assertEquals("vets/index", viewName);
        Set<Vet> vets = (Set<Vet>) model.getAttribute("vets");
        assertNotNull(vets);
        assertEquals(2, vets.size());
    }
}