package com.msr.agenceloc.agence.dto;

import com.msr.agenceloc.adresse.Adresse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AgenceDto(

                Long agenceId,
                @NotNull
                String nom,

                @Email
                String email,

                @NotNull
                @NotBlank
                String tel,

                @NotNull
                Adresse adresse,
                int nombreVehicules
) {
}
