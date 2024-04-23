package com.msr.agenceloc.reservation;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;


    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation reserver(Reservation reservation)
    {
        return this.reservationRepository.save(reservation);
    }


}
