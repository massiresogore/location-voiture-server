package com.msr.agenceloc.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AgenceFourniVehiculeKey implements Serializable {

    @Column(name = "agence_id")
    private Long agenceId;

    @Column(name = "vehicule_id")
    private Long vehiculeId;
}
