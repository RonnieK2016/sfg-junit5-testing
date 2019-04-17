package com.example.sfgtdd.sfgjuni5testing.petclinic.controllers;

import com.example.sfgtdd.sfgjuni5testing.petclinic.fauxspring.BindingResult;
import com.example.sfgtdd.sfgjuni5testing.petclinic.fauxspring.Model;
import com.example.sfgtdd.sfgjuni5testing.petclinic.fauxspring.impl.ModelImpl;
import com.example.sfgtdd.sfgjuni5testing.petclinic.model.Owner;
import com.example.sfgtdd.sfgjuni5testing.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    private OwnerService ownerService;

    @Mock
    private BindingResult bindingResult;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    private OwnerController ownerController;

    @BeforeEach
    void setUp() {
        ownerController = new OwnerController(ownerService);
    }

    @Test
    void testProcessCreationFormSuccess() {
        //given
        Owner owner = new Owner(5L, "James","Woods");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any(Owner.class))).willReturn(owner);

        //when
        String viewName = ownerController.processCreationForm(owner, bindingResult);

        assertEquals("redirect:/owners/" + owner.getId(), viewName);
        then(ownerService).should().save(any(Owner.class));
        then(bindingResult).should().hasErrors();
    }

    @Test
    void testProcessCreationFormFail() {
        Owner owner = new Owner(5L, "James","Woods");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String viewName = ownerController.processCreationForm(owner, bindingResult);

        assertThat("owners/createOrUpdateOwnerForm").isEqualTo(viewName);
        then(ownerService).should(never()).save(any(Owner.class));
        then(bindingResult).should().hasErrors();
    }

    @Test
    void testProcessFormArgCaptor() {
        Owner owner = new Owner(5L, "James","Woods");
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(Arrays.asList(owner));
        Model model = new ModelImpl();

        ownerController.processFindForm(owner, bindingResult, model);

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("%" + owner.getLastName() + "%");
    }

    @Test
    void testProcessFormArgCaptorAnnotation() {
        Owner owner = new Owner(5L, "James","Woods");
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(Arrays.asList(owner));
        Model model = new ModelImpl();

        ownerController.processFindForm(owner, bindingResult, model);

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("%" + owner.getLastName() + "%");
    }
}