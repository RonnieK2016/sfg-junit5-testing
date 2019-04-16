package com.example.sfgtdd.sfgjuni5testing.petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

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

    @Test
    @Disabled
    @DisplayName("Testing Timeout")
    void testTimeout() {
        assertTimeout(Duration.ofMillis(100), () ->{ Thread.sleep(2000);});
    }

    @Test
    @Disabled
    @DisplayName("Testing Preemptive Timeout")
    void testPreemptiveTimeout() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () ->{ Thread.sleep(2000);});
    }

    @Test
    void testAssumptionDemo() {
        assumeTrue("TEST".equalsIgnoreCase("NOT TEST"));
    }
}