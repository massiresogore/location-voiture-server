package com.msr.agenceloc.vehicule.dto;

import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record VehiculeDto (

                Long vehiculeId,
                @NotNull
                 String couleur,

                @Min(1)
                 int poids,

                @JoinColumn(name = "nombre_de_porte")
                @Min(2)
                @Max(4)
                 int nbrPorte,

                @Min(1)
                 int cylindre,

                @Min(1)
                 int longueur,

                 boolean isBooked,
                 String photo,

                @Max(45)
                int prixJournalier
){
}
