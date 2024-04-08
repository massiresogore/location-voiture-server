package com.msr.agenceloc.date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;

@Entity
public class DateReservation {
    @Future //include present
    @Id
    private LocalDate dateReservation;
}
