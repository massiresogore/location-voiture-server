package com.msr.agenceloc.automobile;

import com.msr.agenceloc.client.ClientUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Vehicule extends Automobile  {
    private int nbRoues;

    @JoinColumn(name = "nombre_de_porte")
    @Min(2)
    @Max(4)
    private int nbrPorte;


    public Vehicule() {

    }

    public Vehicule(Long id, String couleur, int poids, int prixJournalier, ClientUser client, int nbRoues, int nbrPorte) {
        super(id, couleur, poids, prixJournalier, client);
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

}
