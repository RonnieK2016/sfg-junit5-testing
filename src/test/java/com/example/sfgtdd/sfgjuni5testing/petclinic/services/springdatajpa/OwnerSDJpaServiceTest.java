package com.example.sfgtdd.sfgjuni5testing.petclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerSDJpaServiceTest {

    private OwnerSDJpaService ownerSDJpaService;

    @BeforeEach
    void setUp() {
        ownerSDJpaService = new OwnerSDJpaService(null, null, null);
    }

    @Test
    @Disabled
    void findByLastName() {
        ownerSDJpaService.findByLastName("Test");
    }

    @Test
    void findById() {

    }
}