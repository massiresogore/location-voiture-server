package com.msr.agenceloc.automobile;

import com.msr.agenceloc.client.ClientUser;
import jakarta.persistence.Entity;

@Entity
public class Camion extends Automobile {
    private int longueur;

    public Camion(){

    }
    public Camion(Long id, String couleur, int poids, int prixJournalier, boolean isBooked, ClientUser client, int longueur) {
        super(id, couleur, poids, prixJournalier, isBooked, client);
        this.longueur = longueur;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }
}
