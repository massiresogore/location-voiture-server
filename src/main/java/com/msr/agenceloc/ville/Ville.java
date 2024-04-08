package com.msr.agenceloc.ville;

import com.msr.agenceloc.adresse.Adresse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ville_id")
    private Long villeId;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private String nom;

    @OneToMany(mappedBy = "ville",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    private List<Adresse> adresses;

    public void addAdress(Adresse adresse){
        if(this.adresses== null){

            this.adresses = new ArrayList<>();
        }
        this.adresses.add(adresse);
        adresse.setVille(this);
    }

    public  void removeAdresse(Adresse adresse){

        this.adresses.remove(adresse);
        adresse.setVille(null);
    }
}
