package com.msr.agenceloc.embeddable.dto;

import java.time.LocalDate;

public record ReservationResponseDto(
    Long clientUserId,
    String clientUserNom,
    Long automobileId,
    LocalDate dateReservation,
    LocalDate  dateDebut,
    LocalDate dateFin,
    int prixJournalier,
    String automobileName


) {
}

