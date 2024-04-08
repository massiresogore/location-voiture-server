# Travail personel Bachelor 3 :Système de Gestion de Location de Véhicules

## <ins> Intervenant:</ins>

1. Massire

## SUJET: Système de Gestion de Location de Véhicules
Développer une application web en SPRING qui permet la gestion d'une agence de
location de véhicules, incluant des voitures et des camions (4 roues) ainsi que des motos
et scooters (2 roues). Le système devra prendre en charge la gestion des véhicules, des
réservations, des utilisateurs avec différents rôles, et permettre aux clients de commenter
les véhicules qu'ils ont loués.

## Gestion des Véhicules :
* Ajouter, modifier et supprimer des véhicules.
* Attribuer des caractéristiques à chaque véhicule : prix journalier, couleur,
  poids. Nombre de portes pour les 4 roues, une longueur pour un camion et
  un cylindre pour les deux roues.
* Distinguer les véhicules en deux catégories : 4 roues (voiture, camion) et 2
  roues (moto, scooter).


## Gestion des Utilisateurs :
* Création de trois types de profils : Admin, Commercial, et Client.
* Les Admins peuvent gérer l'ensemble du système, y compris les véhicules
et tous les utilisateurs.
* Les Commerciaux peuvent gérer les véhicules et voir les réservations.
* Les Clients peuvent réserver des véhicules, voir leurs réservations passées,
et commenter les véhicules qu'ils ont loués
ntribuera à renforcer l'impact et la visibilité des séminaires et des conférences.


## Réservation des Véhicules :
* Permettre aux clients de rechercher des véhicules disponibles et de les
réserver pour des dates spécifiques.
* Afficher le coût total de la location basé sur le prix journalier du véhicule et
la durée de la location.


## Commentaires sur les Véhicules :
* Permettre aux clients de commenter les véhicules qu'ils ont loués après la
fin de la location.
* Afficher les commentaires sur la page de détails du véhicule pour que les
futurs clients puissent les voir.

## <ins>Travail à faire 1 : la conception<ins>
### A. Merise
1. Dictionnaire de données. ✅
2. Déterminations des objets ✅
3. Déterminations des relations ✅
4. Modèle conceptuel des données ✅
5. Modèle logique des données ✅

### A. Uml
1. Diagramme de classe ✅
2. Diagramme de cas d’utilisation ✅

## <ins>Travail à faire 2 <ins>
### A. Création des Entités et Repository ✅
1. Mettez en place votre projet SPRING.
2. Créez toutes les entités nécessaires dans votre IDE. Assurez-vous également de mettre les
   contraintes sur les attributs.
3. Login : taille entre 6 et 8 caractères (a-z) tous en minuscules sans espace.
4. Prénom : taille entre 2 et 30 caractères (a-z).
5. Prix_journalier : de 45.0 € minimum
6. Nombre_porte : minimum 2, maximum 4

