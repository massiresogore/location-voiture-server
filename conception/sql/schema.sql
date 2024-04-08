CREATE TABLE vehicule(
                         vehicule_id COUNTER,
                         couleur VARCHAR(50),
                         poids VARCHAR(50),
                         nbrPorte VARCHAR(50),
                         cylindre VARCHAR(50),
                         longueur VARCHAR(50),
                         photo VARBINARY(50),
                         prixJournalier VARCHAR(50),
                         isBooked LOGICAL,
                         PRIMARY KEY(vehicule_id)
);

CREATE TABLE dateReservation(
                                date_reservation DATE,
                                PRIMARY KEY(date_reservation)
);

CREATE TABLE ville(
                      ville_id INT,
                      nom VARCHAR(50),
                      PRIMARY KEY(ville_id)
);

CREATE TABLE adresse(
                        adresse_id INT,
                        nom VARCHAR(50),
                        numero VARCHAR(50),
                        cp INT,
                        ville_id INT NOT NULL,
                        PRIMARY KEY(adresse_id),
                        FOREIGN KEY(ville_id) REFERENCES ville(ville_id)
);

CREATE TABLE clientUser(
                           client_user_id COUNTER,
                           nom VARCHAR(50) NOT NULL,
                           prenom VARCHAR(50),
                           role VARCHAR(20),
                           email VARCHAR(70),
                           password VARCHAR(200),
                           adresse_id INT NOT NULL,
                           PRIMARY KEY(client_user_id),
                           UNIQUE(nom),
                           UNIQUE(email),
                           FOREIGN KEY(adresse_id) REFERENCES adresse(adresse_id)
);

CREATE TABLE agence(
                       agence_id COUNTER,
                       mom VARCHAR(50),
                       email VARCHAR(70),
                       tel VARCHAR(50),
                       adresse_id INT NOT NULL,
                       PRIMARY KEY(agence_id),
                       FOREIGN KEY(adresse_id) REFERENCES adresse(adresse_id)
);

CREATE TABLE commentaire(
                            commentaire_id COUNTER,
                            description TEXT,
                            dateTime DATETIME,
                            vehicule_id INT NOT NULL,
                            client_user_id INT NOT NULL,
                            PRIMARY KEY(commentaire_id),
                            FOREIGN KEY(vehicule_id) REFERENCES vehicule(vehicule_id),
                            FOREIGN KEY(client_user_id) REFERENCES clientUser(client_user_id)
);

CREATE TABLE reserver(
                         client_user_id INT,
                         vehicule_id INT,
                         date_reservation DATE,
                         dateDebut DATETIME,
                         dateFin DATETIME,
                         PRIMARY KEY(client_user_id, vehicule_id, date_reservation),
                         FOREIGN KEY(client_user_id) REFERENCES clientUser(client_user_id),
                         FOREIGN KEY(vehicule_id) REFERENCES vehicule(vehicule_id),
                         FOREIGN KEY(date_reservation) REFERENCES dateReservation(date_reservation)
);

CREATE TABLE fournir(
                        vehicule_id INT,
                        agence_id INT,
                        quantiteFourni INT,
                        PRIMARY KEY(vehicule_id, agence_id),
                        FOREIGN KEY(vehicule_id) REFERENCES vehicule(vehicule_id),
                        FOREIGN KEY(agence_id) REFERENCES agence(agence_id)
);
