package com.msr.agenceloc.client;

import com.msr.agenceloc.adresse.Adresse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class ClientUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "client_user_id")
    private Long clientUserId;
    @Pattern(regexp = "^[a-z]{6,8}$",
            message = "prenom must be of 6 to 12 length with no special characters")
    @NotNull
    private String nom;
    @Pattern(regexp = "^[a-z]{2,30}$",
            message = "prenom must be of 6 to 12 length with no special characters")
    @NotNull
    private String prenom;

    @NotNull
    private String role;//a voir, cree une entité

    @Email
    @NotNull
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
            message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    @NotNull
    private String password;
    private boolean isBooked;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

}
