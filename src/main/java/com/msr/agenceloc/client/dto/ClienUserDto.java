package com.msr.agenceloc.client.dto;

import com.msr.agenceloc.adresse.Adresse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ClienUserDto(
       Long clientUserId,
       @NotNull
       String nom,
       @Pattern(regexp = "^[a-z]{2,30}$",
       message = "prenom must be of 6 to 12 length with no special characters")
       String prenom,
       @NotNull
       String role,
       @NotNull
       String email,
       @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
               message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
       String password,
       boolean isBooked,
       @NotNull
       Adresse adresse
) {
}
