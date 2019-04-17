package com.example.sfgtdd.sfgjuni5testing.petclinic.controllers;

import com.example.sfgtdd.sfgjuni5testing.petclinic.fauxspring.BindingResult;
import com.example.sfgtdd.sfgjuni5testing.petclinic.model.Owner;
import com.example.sfgtdd.sfgjuni5testing.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    BindingResult bindingResult;

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
        owner.setAddress("Test Address");
        owner.setCity("Test City");
        owner.setTelephone("123456789");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String viewName = ownerController.processCreationForm(owner, bindingResult);

        assertEquals("owners/createOrUpdateOwnerForm", viewName);
        then(ownerService).should(never()).save(any(Owner.class));
        then(bindingResult).should().hasErrors();
    }
}