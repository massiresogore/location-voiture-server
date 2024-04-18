package com.msr.agenceloc.system.exception;

import java.time.LocalDate;

public class DateReservationNotFoundException extends RuntimeException{

    public DateReservationNotFoundException(String objectName, LocalDate id){
        super("La "+objectName+" de réservation :"+id + " n'existe pas");
    }
}
