package com.msr.agenceloc.automobile;

import com.msr.agenceloc.agence.Agence;
import com.msr.agenceloc.image.FileData;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract   class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    @NotNull
    protected String couleur;
    @Min(1)
    protected int poids;
    @Min(45)
    protected int prixJournalier;

    @Column(columnDefinition = "boolean default false")
    protected boolean isBooked;

    protected int stock;

    @OneToMany(mappedBy = "automobile")
    protected List<FileData> fileDatas;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(nullable = false, name = "agence_id")
    private Agence agence;

    public Automobile() {

    }

    public Automobile(Long id, String couleur, int poids, int prixJournalier, boolean isBooked, int stock, Agence agence) {
        this.id = id;
        this.couleur = couleur;
        this.poids = poids;
        this.prixJournalier = prixJournalier;
        this.isBooked = isBooked;
        this.stock = stock;
        this.agence = agence;
    }


    public void addImage(FileData fileData){
        if(this.fileDatas == null){
            this.fileDatas = new ArrayList<>();
        }

        this.fileDatas.add(fileData);
        fileData.setAutomobile(this);
    }


public String getClassName(){

        return this.getClass().getSimpleName();
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

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {

        if(this.isBooked){
            this.stock = stock - 1;
        }else {
        this.stock = stock;
        }

    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public String toString() {
        return "Automobile{" +
                "id=" + id +
                ", couleur='" + couleur + '\'' +
                ", poids=" + poids +
                ", prixJournalier=" + prixJournalier +
                ", isBooked=" + isBooked +
                '}';
    }
}
