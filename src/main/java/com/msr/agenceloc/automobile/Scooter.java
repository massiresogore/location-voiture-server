package com.msr.agenceloc.automobile;

import com.msr.agenceloc.client.ClientUser;
import jakarta.persistence.Entity;

@Entity
public class Scooter extends Automobile {

    private int cylindre;

    public Scooter() {

    }

    public Scooter(Long id, String couleur, int poids, int prixJournalier, boolean isBooked, ClientUser client, int cylindre) {
        super(id, couleur, poids, prixJournalier, isBooked, client);
        this.cylindre = cylindre;
    }

    public int getCylindre() {
        return cylindre;
    }

    public void setCylindre(int cylindre) {
        this.cylindre = cylindre;
    }
}
