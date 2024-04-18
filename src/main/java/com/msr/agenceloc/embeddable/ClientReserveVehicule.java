package com.msr.agenceloc.embeddable;

import com.msr.agenceloc.automobile.Automobile;
import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.date.DateReservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class ClientReserveVehicule {

    @EmbeddedId
    ClientReserveVehiculeKey id;

    @MapsId("clientUserId") //in ClientReserveVehiculeKey classe attribut 'clientUserId'
    @ManyToOne
    @JoinColumn(name = "client_user_id" )
    ClientUser clientUser;

    @MapsId("automobileId")
    @ManyToOne
    @JoinColumn(name = "automobile_id" )
    Automobile automobile;

    @MapsId("dateReservation")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "date_reservation" )
    DateReservation dateReservation;

    @Column(name = "date_debut")
    @NotNull
    LocalDate dateDebut;

    @Column(name = "date_fin")
    @NotNull
    LocalDate dateDeFin;

    @Column(name = "nombre_jour")
    @NotNull
    private int nombreJour;

    @Column(name = "prix_journalier")
    @NotNull
    private int prixJournalier;

    @Column(name = "prix_total_reservation")
    private int prixTotalReservation;


    public ClientReserveVehicule() {
    }

    public ClientReserveVehicule(ClientReserveVehiculeKey id,
                                 ClientUser clientUser,
                                 Automobile automobile,
                                 DateReservation dateReservation,
                                 LocalDate dateDebut,
                                 LocalDate dateDeFin,
                                 int nombreJour,
                                 int prixJournalier) {
        this.id = id;
        this.clientUser = clientUser;
        this.automobile = automobile;
        this.dateReservation = dateReservation;
        this.dateDebut = dateDebut;
        this.dateDeFin = dateDeFin;
        this.nombreJour = nombreJour;
        this.prixJournalier = prixJournalier;
    }

    public void calculPrixTotal(){
       this.prixTotalReservation =  this.prixJournalier * this.nombreJour;
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

    public Automobile getAutomobile() {
        return automobile;
    }

    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
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

    public int getNombreJour() {
        return nombreJour;
    }

    public void setNombreJour(int nombreJour) {
        this.nombreJour = nombreJour;
        this.calculPrixTotal();
    }

    public int getPrixJournalier() {
        return prixJournalier;
    }

    public void setPrixJournalier(int prixJournalier) {
        this.prixJournalier = prixJournalier;
    }

    public int getPrixTotalReservation() {
        return prixTotalReservation;
    }

    public void setPrixTotalReservation(int prixTotalReservation) {
        this.prixTotalReservation = prixTotalReservation;
    }

    @Override
    public String toString() {
        return "ClientReserveVehicule{" +
                "id=" + id +
                ", clientUser=" + clientUser +
                ", automobile=" + automobile +
                ", dateReservation=" + dateReservation +
                ", dateDebut=" + dateDebut +
                ", dateDeFin=" + dateDeFin +
                '}';
    }
}
