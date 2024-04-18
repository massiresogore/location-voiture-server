package com.msr.agenceloc.embeddable;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;


    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ClientReserveVehicule reserver(ClientReserveVehicule clientReserveVehicule)
    {
        return this.reservationRepository.save(clientReserveVehicule);
    }


}
