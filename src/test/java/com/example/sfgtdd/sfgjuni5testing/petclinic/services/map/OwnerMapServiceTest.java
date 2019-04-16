package com.example.sfgtdd.sfgjuni5testing.petclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import com.example.sfgtdd.sfgjuni5testing.petclinic.model.Owner;
import com.example.sfgtdd.sfgjuni5testing.petclinic.model.PetType;
import com.example.sfgtdd.sfgjuni5testing.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("Owner Map Service Test - ")
class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;
    private PetTypeService petTypeService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        ownerMapService = new OwnerMapService(petTypeService, new PetMapService());
    }

    @DisplayName("Verify Zero Owners")
    @Test
    void ownersAreZero() {
        int ownerCount = ownerMapService.findAll().size();

        assertEquals(0, ownerCount);
    }

    @DisplayName("Pet Type - ")
    @Nested
    class TestCreatePetTypes {

        @BeforeEach
        void setUp() {
            PetType petType = new PetType(1L, "Dog");
            PetType petType2 = new PetType(2L, "Cat");
            petTypeService.save(petType);
            petTypeService.save(petType2);
        }

        @DisplayName("Test Pet Count")
        @Test
        void testPetCount() {
            int petTypeCount = petTypeService.findAll().size();

            assertEquals(2, petTypeCount);
        }

        @DisplayName("Save Owners Tests - ")
        @Nested
        class SaveOwnersTests {

            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L, "Before", "Each"));
            }

            @DisplayName("Save Owner")
            @Test
            void saveOwner() {
                Owner owner = new Owner(2L, "Joe", "Buck");

                Owner savedOwner = ownerMapService.save(owner);

                assertNotNull(savedOwner);
            }

            @DisplayName("Save Owners Tests - ")
            @Nested
            class FindOwnersTests {

                @DisplayName("Find Owner")
                @Test
                void findOwner() {

                    Owner foundOwner = ownerMapService.findById(1L);

                    assertAll("Owner's Properties Test",
                            () -> assertNotNull(foundOwner),
                            () -> assertEquals(Long.valueOf(1L), foundOwner.getId()));
                }

                @DisplayName("Find Owner Not Found")
                @Test
                void findOwnerNotFound() {

                    Owner foundOwner = ownerMapService.findById(2L);

                    assertNull(foundOwner);
                }
            }
        }
    }

    @DisplayName("Verify Still Zero Owners")
    @Test
    void ownersAreStillZero() {
        int ownerCount = ownerMapService.findAll().size();

        assertEquals(0, ownerCount);
    }
}