package com.msr.agenceloc.automobile.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record AutomobileDto(

                Long vehiculeId,
                @NotNull
                 String couleur,

                @Min(1)
                 int poids,

                 int nbrPorte,

                int cylindre,
                 
                 int longueur,

                 boolean isBooked,

                @Min(45)
                int prixJournalier,

                int nbRoues,
                int stock,
                Long agenceId
){
}

