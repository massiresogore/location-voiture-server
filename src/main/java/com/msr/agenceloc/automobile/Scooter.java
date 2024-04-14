package com.msr.agenceloc.automobile;

import com.msr.agenceloc.client.ClientUser;
import jakarta.persistence.*;

@Entity
public class Scooter extends Automobile {

    private int cylindre;

    public Scooter() {

    }

    public Scooter(Long id,
                   String couleur,
                   int poids,
                   int prixJournalier,
                   boolean isBooked,
                   String photo,
                   ClientUser client,
                   int cylindre
    ) {
        super(id, couleur, poids, prixJournalier, isBooked, photo, client);
        this.cylindre = cylindre;
    }
}
