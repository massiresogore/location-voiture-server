package com.msr.agenceloc.reservation.dtoResponse;

import java.time.LocalDate;

public record ReservationResponseDto(
    Long clientUserId,
    String clientUserNom,
    Long automobileId,
    LocalDate  dateDebut,
    LocalDate dateFin,
    int prixJournalier,
    String automobileName,
    String agenceName


) {
}

