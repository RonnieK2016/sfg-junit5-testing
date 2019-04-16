package com.example.sfgtdd.sfgjuni5testing.petclinic.controllers;

import com.example.sfgtdd.sfgjuni5testing.petclinic.ControllerTests;
import com.example.sfgtdd.sfgjuni5testing.petclinic.model.OwnerType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.RepeatedTest.*;

class IndexControllerTest implements ControllerTests {

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

    @RepeatedTest(value = 5, name = DISPLAY_NAME_PLACEHOLDER + ": " + CURRENT_REPETITION_PLACEHOLDER
            + " of " + TOTAL_REPETITIONS_PLACEHOLDER)
    @DisplayName("Repeated Test Demo")
    void repeatedTestDemo() { }

    @RepeatedTest(5)
    @DisplayName("Repeated Test with DI")
    void repeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ": "
                + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("Parametrized Test Value")
    @ParameterizedTest(name = "{displayName} - {arguments}")
    @ValueSource(strings = {"Test","Test2","Test3"})
    void parameterizedSourceValueDemo(String testValue) {
        System.out.println("Input value " + testValue);
    }

    @DisplayName("Parametrized Test Value")
    @ParameterizedTest(name = "{displayName} - {arguments}")
    @EnumSource(OwnerType.class)
    void parameterizedEnumSourceDemo(OwnerType ownerType) {
        System.out.println("Input value " + ownerType);
    }
}