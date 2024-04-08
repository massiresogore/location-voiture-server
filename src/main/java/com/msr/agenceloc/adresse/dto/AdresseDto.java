package com.msr.agenceloc.adresse.dto;

import com.msr.agenceloc.ville.Ville;
import jakarta.validation.constraints.NotNull;

public record AdresseDto(
        Long adresseId,
        @NotNull
        String nom,
        int numero,
        @NotNull
        int cp,
        @NotNull
        Ville ville

) { }
