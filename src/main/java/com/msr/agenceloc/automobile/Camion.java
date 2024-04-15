package com.msr.agenceloc.automobile;

import com.msr.agenceloc.agence.Agence;
import jakarta.persistence.Entity;

@Entity
public class Camion extends Automobile {
    private int longueur;

    public Camion(){

    }

    public Camion(Long id, String couleur,
                  int poids, int prixJournalier,
                  boolean isBooked, int stock,
                  Agence agence, int longueur
    ) {
        super(id, couleur, poids, prixJournalier, isBooked, stock, agence);
        this.longueur = longueur;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }


}
