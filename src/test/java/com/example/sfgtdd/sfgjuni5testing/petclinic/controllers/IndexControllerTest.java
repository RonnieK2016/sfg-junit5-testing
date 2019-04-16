package com.example.sfgtdd.sfgjuni5testing.petclinic.controllers;

import com.example.sfgtdd.sfgjuni5testing.extensions.TimeLoggerExtension;
import com.example.sfgtdd.sfgjuni5testing.petclinic.ControllerTests;
import com.example.sfgtdd.sfgjuni5testing.petclinic.model.OwnerType;
import com.example.sfgtdd.sfgjuni5testing.providers.CustomArgumentsProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.RepeatedTest.*;

@ExtendWith(TimeLoggerExtension.class)
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
        assumeTrue("TEST".equalsIgnoreCase("TEST"));
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

    @DisplayName("Parametrized Enum Source")
    @ParameterizedTest(name = "{displayName} - {arguments}")
    @EnumSource(OwnerType.class)
    void parameterizedEnumSourceDemo(OwnerType ownerType) {
        System.out.println("Input value " + ownerType);
    }

    @DisplayName("Parametrized CSV Value Test ")
    @ParameterizedTest(name = "{displayName} - {arguments}")
    @CsvSource(delimiter = ';', value = {"1;2;3","4;5;6","7;8;9"})
    void csvSourceDemo(int arg1, int arg2, int arg3) {
        System.out.println("Input args " + arg1 + " " + arg2 + " " + arg3);
    }

    @DisplayName("Parametrized CSV File Test")
    @ParameterizedTest(name = "{displayName} - {arguments}")
    @CsvFileSource(resources = "/input.csv", delimiter = ';')
    void csvFileSourceDemo(int arg1, int arg2, int arg3) {
        System.out.println("Input args " + arg1 + " " + arg2 + " " + arg3);
    }

    Stream<Arguments> sourceArguments() {
        return Stream.of(Arguments.of(3,2,1),Arguments.of(6,5,4), Arguments.of(9,8,7));
    }

    @DisplayName("Parametrized Method Source Test")
    @ParameterizedTest(name = "{displayName} - {arguments}")
    @MethodSource("sourceArguments")
    void methodSourceValueDemo(int arg1, int arg2, int arg3) {
        System.out.println("Input args " + arg1 + " " + arg2 + " " + arg3);
    }

    @DisplayName("Parametrized Custom Provider Test")
    @ParameterizedTest(name = "{displayName} - {arguments}")
    @ArgumentsSource(CustomArgumentsProvider.class)
    void customArgumentsProviderDemo(int arg1, int arg2, int arg3) {
        System.out.println("Input args " + arg1 + " " + arg2 + " " + arg3);
    }
}