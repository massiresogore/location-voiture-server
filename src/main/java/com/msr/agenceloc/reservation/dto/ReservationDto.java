package com.msr.agenceloc.reservation.dto;

import com.msr.agenceloc.automobile.Automobile;
import com.msr.agenceloc.client.ClientUser;

public record ReservationDto(

        int reservation_id,
        ClientUser client,
        Automobile automobile,
        String dateDebut,
        String dateFin,
        int prixJournalier
) {
}
