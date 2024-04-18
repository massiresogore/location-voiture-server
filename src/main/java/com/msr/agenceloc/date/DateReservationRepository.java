package com.msr.agenceloc.date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DateReservationRepository extends JpaRepository<DateReservation, LocalDate> {

}
