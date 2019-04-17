package com.example.sfgtdd.sfgjuni5testing.petclinic.services.springdatajpa;

import com.example.sfgtdd.sfgjuni5testing.petclinic.model.Speciality;
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
        List<Visit> list = Arrays.asList(new Visit(), new Visit());

        when(visitRepository.findAll()).thenReturn(list);

        Set<Visit> visits = visitSDJpaService.findAll();

        verify(visitRepository).findAll();
        assertNotNull(visits);
        assertEquals(2, visits.size());
    }

    @Test
    void findById() {
        Visit visit = new Visit(1L);

        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));

        Visit savedVisit = visitSDJpaService.findById(1L);

        verify(visitRepository).findById(1L);
        assertNotNull(savedVisit);
        assertEquals(Long.valueOf(1L), savedVisit.getId());
    }

    @Test
    void save() {
        Visit visit = new Visit();

        when(visitRepository.save(any(Visit.class))).thenReturn(visit);

        Visit savedVisit = visitSDJpaService.save(visit);

        verify(visitRepository).save(any(Visit.class));
        assertNotNull(savedVisit);
    }

    @Test
    void delete() {
        visitSDJpaService.delete(new Visit());

        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        visitSDJpaService.deleteById(1L);

        verify(visitRepository).deleteById(1L);
        verify(visitRepository, never()).deleteById(2L);
    }
}