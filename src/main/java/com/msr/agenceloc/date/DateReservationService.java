package com.msr.agenceloc.date;

import com.msr.agenceloc.system.exception.DateReservationNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateReservationService {
    private final DateReservationRepository dateReservationRepository;


    public DateReservationService(DateReservationRepository dateReservationRepository) {
        this.dateReservationRepository = dateReservationRepository;
    }

    public DateReservation findByDate(LocalDate dateReservationId)
    {
      return   this.dateReservationRepository.findById(dateReservationId).orElseThrow(()->new DateReservationNotFoundException("date", dateReservationId));
    }



}
