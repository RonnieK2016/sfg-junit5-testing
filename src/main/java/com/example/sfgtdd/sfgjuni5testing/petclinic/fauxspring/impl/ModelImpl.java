package com.example.sfgtdd.sfgjuni5testing.petclinic.fauxspring.impl;

import com.example.sfgtdd.sfgjuni5testing.petclinic.fauxspring.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelImpl implements Model {

    private HashMap<String, Object> modelAttributes = new HashMap<>();


    @Override
    public void addAttribute(String key, Object o) {
        modelAttributes.put(key, o);
    }

    @Override
    public void addAttribute(Object o) {
        //storing at NULL key
        List<Object> listOfObjects = (List<Object>) modelAttributes.get(null);
        if(listOfObjects == null) {
            listOfObjects = new ArrayList<>();
            modelAttributes.put(null, listOfObjects);
        }

        listOfObjects.add(o);
    }

    @Override
    public Object getAttribute(String key) {
        return modelAttributes.get(key);
    }
}
