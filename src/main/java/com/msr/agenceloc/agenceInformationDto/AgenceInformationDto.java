package com.msr.agenceloc.agenceInformationDto;

public record AgenceInformationDto(
        long totalAgence,
        long totalVehicule,
        long totalCamion,
        long totalScooter,
        long totalClient,
        Long totalAutomobile,
        long totalReservation
       /* int totalPrixVoitureCamionQuatreRoues,

        int totalPrixJournalerScooterDeuxRoues,
        int totalGeneralDeuxRouesEtQuatreRoues,
        int pourcentageCamionReserver,
        int pourcentageVehiculeReserver,
        int pourcentageScooterReserver*/
) {
}
