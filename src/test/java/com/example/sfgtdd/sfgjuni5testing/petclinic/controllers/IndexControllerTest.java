package com.example.sfgtdd.sfgjuni5testing.petclinic.controllers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Tag("controllers")
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

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testOnWindows() {
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testOnMac() {
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testOnJava8() {
    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void testOnJava11() {
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "PROMPT", matches = "$P$G")
    void testEnvironmentProperty() {
    }
}