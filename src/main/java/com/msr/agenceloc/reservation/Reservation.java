package com.msr.agenceloc.reservation;

import com.msr.agenceloc.automobile.Automobile;
import com.msr.agenceloc.client.ClientUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long reservation_id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false )
    ClientUser clientUser;

    @ManyToOne
    @JoinColumn(name = "automobile_id", nullable = false )
    Automobile automobile;

    @NotNull
    LocalDate dateDebut;
    @NotNull
    LocalDate dateFin;
    @Column(name = "prix_journalier")
    @NotNull
    private int prixJournalier;

    public Reservation() {
    }

    public Reservation(Long reservation_id, ClientUser clientUser, Automobile automobile, LocalDate dateDebut, LocalDate dateFin, int prixJournalier) {
        this.reservation_id = reservation_id;
        this.clientUser = clientUser;
        this.automobile = automobile;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixJournalier = prixJournalier;
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

    public int getPrixJournalier() {
        return prixJournalier;
    }

    public void setPrixJournalier(int prixJournalier) {
        this.prixJournalier = prixJournalier;
    }

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}
