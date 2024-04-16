package com.msr.agenceloc.agenceInformationDto;

public record AgenceInformationDto(
        int totalAgence,
        int totalVehicule,
        int totalCamion,
        int totalScooter,
        int totalClient,
        int totalReservation,
        int totalPrixVoitureCamionQuatreRoues,

        int totalPrixJournalerScooterDeuxRoues,
        int totalGeneralDeuxRouesEtQuatreRoues
) {
}
