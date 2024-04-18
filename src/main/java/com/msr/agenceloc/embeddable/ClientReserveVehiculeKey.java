package com.msr.agenceloc.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;


@Embeddable
public class ClientReserveVehiculeKey implements Serializable {

    @Column(name = "client_user_id")
    private Long clientUserId;

    @Column(name = "automobile_id")
    private Long automobileId;

    @Column(name = "date_reservation")
    private LocalDate dateReservation;

    public ClientReserveVehiculeKey() {
    }

    public ClientReserveVehiculeKey(Long clientUserId, Long automobileId, LocalDate dateReservation) {
        this.clientUserId = clientUserId;
        this.automobileId = automobileId;
        this.dateReservation = dateReservation;
    }

    public Long getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    public Long getVehiculeId() {
        return automobileId;
    }

    public void setVehiculeId(Long automobileId) {
        this.automobileId = automobileId;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Override
    public String toString() {
        return "ClientReserveVehiculeKey{" +
                "clientUserId=" + clientUserId +
                ", automobileId=" + automobileId +
                ", dateReservation=" + dateReservation +
                '}';
    }
}
