package com.example.sfgtdd.sfgjuni5testing.petclinic.services.springdatajpa;

import com.example.sfgtdd.sfgjuni5testing.petclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

    @Mock
    private VetRepository vetRepository;

    private VetSDJpaService vetSDJpaService;

    @BeforeEach
    void setUp() {
        vetSDJpaService = new VetSDJpaService(vetRepository);
    }

    @Test
    void deleteById() {
        vetSDJpaService.deleteById(1L);
        vetSDJpaService.deleteById(1L);

        verify(vetRepository, times(2)).deleteById(1L);
        verify(vetRepository, atLeast(1)).deleteById(1L);
        verify(vetRepository, atLeastOnce()).deleteById(1L);
        verify(vetRepository, atMost(5)).deleteById(1L);
        verify(vetRepository, never()).deleteById(2L);
    }
}