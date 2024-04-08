package com.msr.agenceloc.embeddable;

import com.msr.agenceloc.agence.Agence;
import com.msr.agenceloc.vehicule.Vehicule;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AgenceFourniVehicule {
    @EmbeddedId
    AgenceFourniVehiculeKey id;

    @MapsId("agenceId")
    @ManyToOne
    @JoinColumn(name = "agence_id")
    Agence agence;

    @MapsId("vehiculeId")
    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    Vehicule vehicule;

    @Column(name = "quantite_fourni")
    @NotNull
    int quantiteFourni;
}
