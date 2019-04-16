package com.example.sfgtdd.sfgjuni5testing.petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    private IndexController indexController;


    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @Test
    void index() {
        assertEquals("index", indexController.index(), () -> "Custom message to show on error!");
        //another way to validate
        assertTrue("index".equals(indexController.index()));
    }

    @Test
    void oupsHandler() {
        assertEquals("notimplemented", indexController.oupsHandler(), () -> "Another Custom Message to be shown on error!");
    }
}