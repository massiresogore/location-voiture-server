package com.msr.agenceloc.automobile.subclasse;

import com.msr.agenceloc.agence.Agence;
import com.msr.agenceloc.automobile.Automobile;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Max;

@Entity
public class Vehicule extends Automobile {

    private int nbRoues;

    @JoinColumn(name = "nombre_de_porte")
    //@Min(2)
    @Max(4)
    private int nbrPorte;


    public Vehicule() {

    }

    public Vehicule(Long id, String couleur, int poids, int prixJournalier,
                    boolean isBooked, int stock,
                    Agence agence,
                    int nbRoues, int nbrPorte) {
        super(id, couleur, poids, prixJournalier, isBooked, stock, agence);
        this.nbRoues = nbRoues;
        this.nbrPorte = nbrPorte;
    }

    public int getNbRoues() {
        return nbRoues;
    }

    public void setNbRoues(int nbRoues) {
        this.nbRoues = nbRoues;
    }

    public int getNbrPorte() {
        return nbrPorte;
    }

    public void setNbrPorte(int nbrPorte) {
        this.nbrPorte = nbrPorte;
    }


    @Override
    public String toString() {
        return "Vehicule{" +
                "nbRoues=" + nbRoues +
                ", nbrPorte=" + nbrPorte +
                '}';
    }

    @Override
    public String getClassName() {
        return super.getClassName();
    }
}
