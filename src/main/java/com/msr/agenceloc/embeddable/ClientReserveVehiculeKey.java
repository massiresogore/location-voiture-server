package com.msr.agenceloc.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;


@Embeddable
public class ClientReserveVehiculeKey implements Serializable {

    @Column(name = "client_user_id")
    private Long clientUserId;

    @Column(name = "vehicule_id")
    private Long vehiculeId;

    @Column(name = "date_reservation")
    private LocalDate dateReservation;

    public ClientReserveVehiculeKey() {
    }

    public ClientReserveVehiculeKey(Long clientUserId, Long vehiculeId, LocalDate dateReservation) {
        this.clientUserId = clientUserId;
        this.vehiculeId = vehiculeId;
        this.dateReservation = dateReservation;
    }

    public Long getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    public Long getVehiculeId() {
        return vehiculeId;
    }

    public void setVehiculeId(Long vehiculeId) {
        this.vehiculeId = vehiculeId;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }
}
