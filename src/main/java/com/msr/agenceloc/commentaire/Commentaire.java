package com.msr.agenceloc.commentaire;

import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.vehicule.Vehicule;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "commentaire_id")
    private Long commentaireId;

    @NotNull
    @Size(min = 10, max = 500)
    private String description;

    @Future
    @NotNull
    private LocalDate dateTime = LocalDate.now();

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicule vehicule;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(name = "client_user_id", nullable = false)
    private ClientUser clientUSer;
}
