package com.msr.agenceloc.embeddable.dto;

public record ReservationDto(
        String dateDebut,
        String dateFin,
        int nombreJour,
        int prixJournalier,
        int prixTotalReservation,
        String designation

) {
}
