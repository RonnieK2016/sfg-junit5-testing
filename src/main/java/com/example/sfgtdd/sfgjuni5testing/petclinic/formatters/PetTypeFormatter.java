package com.example.sfgtdd.sfgjuni5testing.petclinic.formatters;

import com.example.sfgtdd.sfgjuni5testing.petclinic.services.PetTypeService;
import com.example.sfgtdd.sfgjuni5testing.petclinic.fauxspring.Formatter;
import com.example.sfgtdd.sfgjuni5testing.petclinic.model.PetType;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;


public class PetTypeFormatter implements Formatter {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();

        for (PetType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }

        throw new ParseException("type not found: " + text, 0);
    }
}
