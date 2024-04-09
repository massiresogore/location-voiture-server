package com.msr.agenceloc.vehicule;

import com.msr.agenceloc.embeddable.AgenceFourniVehicule;
import com.msr.agenceloc.embeddable.ClientReserveVehicule;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "vehicule_id")
    private Long vehiculeId;

    @NotNull
    private String couleur;

    @Min(1)
    private int poids;

    @JoinColumn(name = "nombre_de_porte")
    @Min(2)
    @Max(4)
    private int nbrPorte;

    @Min(1)
    private int cylindre;

    @Min(1)
    private int longueur;

    private boolean isBooked;

    private String photo;

    @Min(45)
    private int prixJournalier;

    @OneToMany(mappedBy = "vehicule",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    List<AgenceFourniVehicule> agenceFourniVehicules;

    @OneToMany(
            mappedBy = "vehicule",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    List<ClientReserveVehicule> clientReserveVehicules;

}
