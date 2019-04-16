package com.example.sfgtdd.sfgjuni5testing.petclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {

    @Test
    void nestedAssertionsDemo() {
        //when
        Owner owner = new Owner(1L, "James","Woods");
        owner.setAddress("Test Address");
        owner.setCity("Test City");
        owner.setTelephone("123456789");

        //given
        //.....

        //then
        assertAll("Full Owner Test Set",
                () -> assertAll("Person Properties Test",
                        () -> assertEquals(Long.valueOf(1L), owner.getId(), "Id Validation Failed"),
                        () -> assertEquals("James", owner.getFirstName(), "First Name Validation Failed"),
                        () -> assertEquals("Woods", owner.getLastName(), "Last Name Validation Failed")
                ),
                () -> assertAll("Owner's Properties Test",
                        () -> assertEquals("Test Address", owner.getAddress(), "Address Validation Failed"),
                        () -> assertEquals("Test City", owner.getCity(), "City Validation Failed"),
                        () -> assertEquals("123456789", owner.getTelephone(), "Telephone Validation Failed"))
        );
    }

}