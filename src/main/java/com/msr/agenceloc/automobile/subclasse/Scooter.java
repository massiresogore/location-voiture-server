package com.msr.agenceloc.automobile.subclasse;

import com.msr.agenceloc.agence.Agence;
import com.msr.agenceloc.automobile.Automobile;
import jakarta.persistence.Entity;

@Entity
public class Scooter extends Automobile {

    private int cylindre;

    public Scooter() {

    }

    public Scooter(Long id, String couleur, int poids, int prixJournalier, boolean isBooked, int stock, Agence agence, int cylindre) {
        super(id, couleur, poids, prixJournalier, isBooked, stock, agence);
        this.cylindre = cylindre;
    }

    public int getCylindre() {
        return cylindre;
    }

    public void setCylindre(int cylindre) {
        this.cylindre = cylindre;
    }
}
