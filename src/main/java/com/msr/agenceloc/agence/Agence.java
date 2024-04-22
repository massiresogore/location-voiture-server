package com.msr.agenceloc.agence;

import com.msr.agenceloc.adresse.Adresse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "agence_id")
    private Long agenceId;

    @NotNull
    private String nom;

    @Email
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    private String tel;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

   /* @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Automobile> automobiles;*/

   /* public void addAuto(Automobile automobile){
        if(this.automobiles == null){
            this.automobiles = new ArrayList<>();
        }
        this.automobiles.add(automobile);
        automobile.setAgence(this);
    }*/

//    public int getNombreOfAutomobile() {
//        return automobiles.size();
//    }

    @Override
    public String toString() {
        return "Agence{" +
                "agenceId=" + agenceId +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

}

/*
{
        "password": "Ma1#legende",
        "enable": true,
        "role":"user",
        "nom": "eyenga",
        "prenom": "sogore",
        "email": "eyenga@gmail.com",
        "adresse": {
        "cp":"87990",
        "nom": "eyenga",
        "numero": "90",
        "ville": {
        "nom": "Melun"
        }
        }
        }*/
