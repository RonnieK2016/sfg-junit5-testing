package com.example.sfgtdd.sfgjuni5testing.petclinic.services.springdatajpa;

import com.example.sfgtdd.sfgjuni5testing.petclinic.model.Speciality;
import com.example.sfgtdd.sfgjuni5testing.petclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    private SpecialtyRepository specialtyRepository;

    private SpecialitySDJpaService specialitySDJpaService;

    @BeforeEach
    void setUp() {
        specialitySDJpaService = new SpecialitySDJpaService(specialtyRepository);
    }

    @Test
    void testFindById() {
        //given
        Speciality speciality = new Speciality(1L, "Test");

        //when
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        //then
        Speciality returnedSpeciality = specialitySDJpaService.findById(1L);
        assertNotNull(returnedSpeciality);
        assertEquals(Long.valueOf(1L), returnedSpeciality.getId());
        assertEquals("Test", returnedSpeciality.getDescription());
    }

    @Test
    void delete() {
        specialitySDJpaService.delete(new Speciality());
    }

    @Test
    void deleteById() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        verify(specialtyRepository, times(2)).deleteById(1L);
        verify(specialtyRepository, atLeast(1)).deleteById(1L);
        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
        verify(specialtyRepository, atMost(5)).deleteById(1L);
        verify(specialtyRepository, never()).deleteById(2L);
    }
}