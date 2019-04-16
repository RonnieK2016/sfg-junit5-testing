package com.example.sfgtdd.sfgjuni5testing.petclinic.services;

import com.example.sfgtdd.sfgjuni5testing.petclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
 }
