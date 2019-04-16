package com.example.sfgtdd.sfgjuni5testing.petclinic.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tag("model")
class PersonTest {

    @Test
    void groupedAssertionsDemo() {

        //given
        Person person = new Person(1L, "Jessie", "James");

        //when

        //then
        assertAll("Person Validation Set",
                () -> assertEquals("Jessie", person.getFirstName(), "First Name Validation Failed"),
                () -> assertEquals("James", person.getLastName(), "Last Name Validation Failed"),
                () -> assertEquals(Long.valueOf(1L), person.getId(), "Id Validation Failed")
                );

        assertThat(person).extracting("id","firstName","lastName").containsExactly(1L, "Jessie", "James");
    }

}