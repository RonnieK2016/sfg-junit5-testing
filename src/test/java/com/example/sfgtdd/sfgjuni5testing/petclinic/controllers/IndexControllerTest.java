package com.example.sfgtdd.sfgjuni5testing.petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    private IndexController indexController;


    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @Test
    @DisplayName("Testing Proper View Name for Index Page")
    void index() {
        assertEquals("index", indexController.index(), () -> "Custom message to show on error!");
        //another way to validate
        assertTrue("index".equals(indexController.index()));
    }

    @Test
    @DisplayName("Testing Not Implemented Feature View Name")
    void oupsHandler() {
        assertEquals("notimplemented", indexController.oupsHandler(), () -> "Another Custom Message to be shown on error!");
    }

    @Test
    @DisplayName("Testing Exception for GetAll method")
    void testNotImplementedException() {
        assertThrows(NotImplementedException.class, () -> indexController.getAll());
    }
}