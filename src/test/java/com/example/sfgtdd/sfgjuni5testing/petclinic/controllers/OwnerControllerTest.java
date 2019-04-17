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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock(lenient = true)
    private OwnerService ownerService;

    @Mock
    private BindingResult bindingResult;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    private OwnerController ownerController;

    @BeforeEach
    void setUp() {
        ownerController = new OwnerController(ownerService);
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).will(invocation -> {
            String argValue = invocation.getArgument(0);

            List<Owner> owners = new ArrayList<>();

            if(argValue.equals("%Woods%")) {
               owners.add(new Owner(5L, "James","Woods"));
            }
            else if(argValue.equals("%%")) {
                owners.add(new Owner(5L, "James","Woods"));
                owners.add(new Owner(6L, "James","Woods"));
            }

            return owners;
        });

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
    void testProcessFormArgCaptorAnnotation() {
        Owner owner = new Owner(5L, "James","Woods");
        Model model = new ModelImpl();

        String viewName = ownerController.processFindForm(owner, bindingResult, model);

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("%" + owner.getLastName() + "%");

        assertThat(viewName).isEqualTo("redirect:/owners/" + owner.getId());
    }

    @Test
    void testProcessFormLastNameEmpty() {
        Owner owner = new Owner(5L, "James",null);
        Model model = new ModelImpl();

        String viewName = ownerController.processFindForm(owner, bindingResult, model);

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("%%");

        assertTrue(model.hasAttribute("selections"));

        assertThat(viewName).isEqualTo("owners/ownersList");
    }

    @Test
    void testProcessFormResultsEmpty() {
        Owner owner = new Owner(5L, "James","Test");
        Model model = new ModelImpl();

        String viewName = ownerController.processFindForm(owner, bindingResult, model);

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("%" + owner.getLastName() + "%");

        then(bindingResult).should().rejectValue(anyString(), anyString(), anyString());

        assertThat(viewName).isEqualTo("owners/findOwners");
    }
}