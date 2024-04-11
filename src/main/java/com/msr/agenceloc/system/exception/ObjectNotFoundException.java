package com.msr.agenceloc.system.exception;

public class ObjectNotFoundException extends RuntimeException{

    public  ObjectNotFoundException( String objectName,Long id){
        super("Lobject "+objectName+" que vous recherchez avec l'id :"+id + " n'existe pas");
    }
}
