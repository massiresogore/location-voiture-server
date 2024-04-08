package com.msr.agenceloc.system.exception;

public class ObjectNotFoundException extends RuntimeException{

    public  ObjectNotFoundException(Long id, String objectName){
        super("Lobject "+objectName+" que vous recherchez avec l'id :"+id + " n'existe pas");
    }
}
