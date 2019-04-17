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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
        given(specialtyRepository.findById(anyLong())).willReturn(Optional.of(speciality));

        //when
        Speciality returnedSpeciality = specialitySDJpaService.findById(1L);

        //then
        then(specialtyRepository).should(times(1)).findById(anyLong());
        assertNotNull(returnedSpeciality);
        assertEquals(Long.valueOf(1L), returnedSpeciality.getId());
        assertEquals("Test", returnedSpeciality.getDescription());
    }

    @Test
    void delete() {
        //when
        specialitySDJpaService.delete(new Speciality());

        then(specialtyRepository).should().delete(any(Speciality.class));
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        //when
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        //then
        then(specialtyRepository).should(times(2)).deleteById(1L);
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
        then(specialtyRepository).should(atMost(5)).deleteById(1L);
        then(specialtyRepository).should(never()).deleteById(2L);
    }
}