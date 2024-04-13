package com.msr.agenceloc.embeddable;

import com.msr.agenceloc.automobile.Vehicule;
import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.date.DateReservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClientReserveVehicule {

    @EmbeddedId
    ClientReserveVehiculeKey id;

    @MapsId("clientUserId") //in ClientReserveVehiculeKey classe attribut 'clientUserId'
    @ManyToOne
    @JoinColumn(name = "client_user_id" )
    ClientUser clientUser;

    @MapsId("vehiculeId")
    @ManyToOne
    @JoinColumn(name = "vehicule_id" )
    Vehicule vehicule;

    @MapsId("dateReservation")
    @ManyToOne
    @JoinColumn(name = "date_reservation" )
    DateReservation dateReservation;

    @Column(name = "date_debut")
    @NotNull
    LocalDate dateDebut;

    @Column(name = "date_fin")
    @NotNull
    LocalDate dateDeFin;
}
