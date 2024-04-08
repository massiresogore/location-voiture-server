package com.msr.agenceloc.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ClientReserveVehiculeKey implements Serializable {

    @Column(name = "client_user_id")
    private Long clientUserId;

    @Column(name = "vehicule_id")
    private Long vehiculeId;

    @Column(name = "date_reservation")
    private LocalDate dateReservation;

}
