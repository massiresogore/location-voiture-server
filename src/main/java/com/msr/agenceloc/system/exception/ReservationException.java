package com.msr.agenceloc.system.exception;

public class ReservationException extends RuntimeException{

    public ReservationException(String objectName){
        super("La "+objectName+" n'est pas faite");
    }
}
