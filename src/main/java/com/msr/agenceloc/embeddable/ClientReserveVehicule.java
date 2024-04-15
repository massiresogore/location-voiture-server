package com.msr.agenceloc.embeddable;

import com.msr.agenceloc.automobile.Vehicule;
import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.date.DateReservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

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

    public ClientReserveVehicule() {
    }

    public ClientReserveVehicule(ClientReserveVehiculeKey id, ClientUser clientUser, Vehicule vehicule, DateReservation dateReservation, LocalDate dateDebut, LocalDate dateDeFin) {
        this.id = id;
        this.clientUser = clientUser;
        this.vehicule = vehicule;
        this.dateReservation = dateReservation;
        this.dateDebut = dateDebut;
        this.dateDeFin = dateDeFin;
    }

    public ClientReserveVehiculeKey getId() {
        return id;
    }

    public void setId(ClientReserveVehiculeKey id) {
        this.id = id;
    }

    public ClientUser getClientUser() {
        return clientUser;
    }

    public void setClientUser(ClientUser clientUser) {
        this.clientUser = clientUser;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public DateReservation getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(DateReservation dateReservation) {
        this.dateReservation = dateReservation;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateDeFin() {
        return dateDeFin;
    }

    public void setDateDeFin(LocalDate dateDeFin) {
        this.dateDeFin = dateDeFin;
    }
}
