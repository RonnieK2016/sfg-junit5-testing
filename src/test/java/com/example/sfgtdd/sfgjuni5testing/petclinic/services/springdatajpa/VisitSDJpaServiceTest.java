package com.example.sfgtdd.sfgjuni5testing.petclinic.services.springdatajpa;

import com.example.sfgtdd.sfgjuni5testing.petclinic.model.Visit;
import com.example.sfgtdd.sfgjuni5testing.petclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    private VisitRepository visitRepository;

    private VisitSDJpaService visitSDJpaService;

    @BeforeEach
    void setUp() {
        visitSDJpaService = new VisitSDJpaService(visitRepository);
    }

    @Test
    void findAll() {
        //given
        List<Visit> list = Arrays.asList(new Visit(), new Visit());
        given(visitRepository.findAll()).willReturn(list);

        //when
        Set<Visit> visits = visitSDJpaService.findAll();

        //then
        then(visitRepository).should().findAll();
        assertNotNull(visits);
        assertEquals(2, visits.size());
    }

    @Test
    void findById() {
        //given
        Visit visit = new Visit(1L);
        given(visitRepository.findById(1L)).willReturn(Optional.of(visit));

        //when
        Visit savedVisit = visitSDJpaService.findById(1L);

        //then
        then(visitRepository).should().findById(1L);
        assertNotNull(savedVisit);
        assertEquals(Long.valueOf(1L), savedVisit.getId());
    }

    @Test
    void save() {
        //given
        Visit visit = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visit);

        //when
        Visit savedVisit = visitSDJpaService.save(visit);

        //then
        then(visitRepository).should().save(any(Visit.class));
        assertNotNull(savedVisit);
    }

    @Test
    void delete() {
        //when
        visitSDJpaService.delete(new Visit());

        //then
        then(visitRepository).should().delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        //when
        visitSDJpaService.deleteById(1L);

        //then
        then(visitRepository).should().deleteById(1L);
        then(visitRepository).should(never()).deleteById(2L);
    }

    @Test
    void testThrowException1() {
        doThrow(new RuntimeException("Test")).when(visitRepository).deleteById(1L);

        assertThrows(RuntimeException.class, () -> visitRepository.deleteById(1L));

        then(visitRepository).should().deleteById(1L);
    }

    @Test
    void testThrowException2() {
        given(visitRepository.findById(1L)).willThrow(RuntimeException.class);

        assertThrows(RuntimeException.class, () -> visitRepository.findById(1L));

        then(visitRepository).should().findById(1L);
    }

    @Test
    void testThrowException3() {
        willThrow(RuntimeException.class).given(visitRepository).delete(any(Visit.class));

        assertThrows(RuntimeException.class, () -> visitRepository.delete(new Visit()));

        then(visitRepository).should().delete(any(Visit.class));
    }

    @Test
    void testSaveArgMatcher() {
        //given
        final Long ID = 1L;
        Visit visit = new Visit(ID);
        given(visitRepository.save(argThat(argument -> argument.getId().equals(ID)))).willReturn(visit);

        //when
        Visit savedVisit = visitSDJpaService.save(visit);

        //then
        then(visitRepository).should().save(any(Visit.class));
        assertNotNull(savedVisit);
    }

}