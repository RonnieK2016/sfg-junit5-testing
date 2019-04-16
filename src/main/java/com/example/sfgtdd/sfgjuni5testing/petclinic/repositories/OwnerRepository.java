package com.example.sfgtdd.sfgjuni5testing.petclinic.repositories;


import com.example.sfgtdd.sfgjuni5testing.petclinic.model.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
