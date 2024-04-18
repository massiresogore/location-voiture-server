package com.msr.agenceloc.date;

import com.msr.agenceloc.embeddable.ClientReserveVehicule;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DateReservation {

   // @Future //include present
    @Id
    private LocalDate dateReservation;


    public DateReservation() {
    }

    public DateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }


    public LocalDate getDateReservation() {
        return dateReservation;
    }

    @OneToMany(mappedBy = "dateReservation",cascade = CascadeType.ALL)
    List<ClientReserveVehicule> clientReserveVehicules = new ArrayList<>();

    public void addReserv(ClientReserveVehicule clientReserveVehicule)
    {
        this.clientReserveVehicules.add(clientReserveVehicule);
        clientReserveVehicule.setDateReservation(this);
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Override
    public String toString() {
        return "DateReservation{" +
                "dateReservation=" + dateReservation +
                '}';
    }
}
