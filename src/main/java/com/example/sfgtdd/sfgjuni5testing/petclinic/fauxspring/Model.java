package com.example.sfgtdd.sfgjuni5testing.petclinic.fauxspring;

public interface Model {

    void addAttribute(String key, Object o);

    void addAttribute(Object o);

    Object getAttribute(String key);
}
