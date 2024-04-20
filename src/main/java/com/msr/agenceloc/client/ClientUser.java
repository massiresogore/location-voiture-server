package com.msr.agenceloc.client;

import com.msr.agenceloc.adresse.Adresse;
import com.msr.agenceloc.embeddable.ClientReserveVehicule;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;


@Entity
public class ClientUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "client_user_id")
    private Long clientUserId;
    @Pattern(regexp = "^[a-z]{6,8}$",
            message = "prenom must be of 6 to 8 length with no special characters")
    @NotNull
    @Column(unique = true)
    private String nom;
    @Pattern(regexp = "^[a-z]{2,30}$",
            message = "prenom must be of 6 to 30 length with no special characters")
    @NotNull
    private String prenom;

    @NotNull
    private String roles;//a voir, cree une entité

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,100}$",
//            message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    @NotNull
    private String password;

    private boolean enable;

    @ManyToOne(
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    @OneToMany(mappedBy = "clientUser",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    List<ClientReserveVehicule> clientReserveVehicules;

    public void addReservation(
                               ClientReserveVehicule clientReserveVehicule
    )
    {

        if (clientReserveVehicules==null){
            this.clientReserveVehicules = new ArrayList<>();
        }
        clientReserveVehicule.setClientUser(this);

    }

    public ClientUser() {
    }

    public ClientUser(Long clientUserId, String nom, String prenom, String roles, String email, String password, boolean enable, Adresse adresse) {
        this.clientUserId = clientUserId;
        this.nom = nom;
        this.prenom = prenom;
        this.roles = roles;
        this.email = email;
        this.password = password;
        this.enable = enable;
        this.adresse = adresse;
    }

    public Long getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(Long clientUserId) {
        this.clientUserId = clientUserId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String role) {
        this.roles = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "ClientUser{" +
                "clientUserId=" + clientUserId +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", role='" + roles + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", adresse=" + adresse +
                '}';
    }
}
