package com.msr.agenceloc.automobile;

import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.embeddable.AgenceFourniVehicule;
import com.msr.agenceloc.embeddable.ClientReserveVehicule;
import com.msr.agenceloc.image.FileData;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract   class Automobile {
//public abstract class Automobile {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    private String couleur;
    @Min(1)
    private int poids;
    @Min(45)
    private int prixJournalier;

    @Column(columnDefinition = "boolean default false")
    private boolean isBooked;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "client_user_id")
    private ClientUser client;

    @OneToMany(mappedBy = "automobile")
    private List<FileData> fileDatas;


    @OneToMany(mappedBy = "vehicule",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )

    List<AgenceFourniVehicule> agenceFourniVehicules;

    @OneToMany(
            mappedBy = "vehicule",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    List<ClientReserveVehicule> clientReserveVehicules;

    public Automobile() {

    }

    public Automobile(Long id, String couleur, int poids, int prixJournalier, boolean isBooked, ClientUser client) {
        this.id = id;
        this.couleur = couleur;
        this.poids = poids;
        this.prixJournalier = prixJournalier;
        this.isBooked = isBooked;
        this.client = client;
    }

/*   // Méthodes abstraites
    public abstract void demarrer();

    public abstract void arreter();*/

    //Add Image

    public void addImage(FileData fileData){
        if(this.fileDatas == null){
            this.fileDatas = new ArrayList<>();
        }

        this.fileDatas.add(fileData);
        fileData.setAutomobile(this);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getPrixJournalier() {
        return prixJournalier;
    }

    public void setPrixJournalier(int prixJournalier) {
        this.prixJournalier = prixJournalier;
    }

    public ClientUser getClient() {
        return client;
    }

    public void setClient(ClientUser client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "id=" + id +
                ", couleur='" + couleur + '\'' +
                ", poids=" + poids +
                ", prixJournalier=" + prixJournalier +
                ", isBooked=" + isBooked +
                ", client=" + client +
                '}';
    }
}
