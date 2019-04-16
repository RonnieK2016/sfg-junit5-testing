package com.example.sfgtdd.sfgjuni5testing.petclinic.controllers;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class IndexController {

    public String index(){

        return "index";
    }

    public String oupsHandler(){
        return "notimplemented";
    }


    public String getAll() {
        throw new NotImplementedException();
    }
}
