package com.msr.agenceloc.adresse;

import com.msr.agenceloc.ville.Ville;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "addresse_id")
    private Long adresseId;

    @NotNull
    @NotBlank
    private String nom;

    @NotNull
    @NotBlank
    private int numero;

    @NotNull
    @JoinColumn(name = "code_postal")
    private int cp;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ville_id", nullable = false)
    private Ville ville;


}
