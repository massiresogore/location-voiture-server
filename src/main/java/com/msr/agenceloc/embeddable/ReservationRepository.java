package com.msr.agenceloc.embeddable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ClientReserveVehicule,ClientReserveVehiculeKey> {


    @Query(value = "SELECT SUM(`prix_reservation`) FROM reservation r WHERE r.designation='Scooter';", nativeQuery = true)
    Optional<Integer> totalPrixScooterReserve();

    @Query(value = "SELECT SUM(`prix_reservation`) FROM reservation r WHERE r.designation='Vehicule' OR r.designation='Camion' ;", nativeQuery = true)
    Optional<Integer> findTotalPrixVehiculeAndCamionReserve();

  /*  @Query(value = "select COUNT(*) from vehicule as v INNER JOIN agence as a on v.agence_id=a.agence_id WHERE v.is_booked =1;", nativeQuery = true)
    int findAllByIdMatches(Long id);
*/

}
