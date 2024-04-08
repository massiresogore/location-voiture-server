package com.msr.agenceloc.agence;

import com.msr.agenceloc.adresse.Adresse;
import com.msr.agenceloc.embeddable.AgenceFourniVehicule;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "agence_id")
    private Long agenceId;

    @NotNull
    private String nom;

    @Email
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    private String tel;


    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @OneToMany(mappedBy = "agence",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    List<AgenceFourniVehicule> agenceFourniVehicules;
}
