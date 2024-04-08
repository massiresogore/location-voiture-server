package com.msr.agenceloc.ville.dto;

import jakarta.validation.constraints.NotNull;

public record VilleDto(
        Long villeId,
        @NotNull
        String nom
) {
}
