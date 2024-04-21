package com.msr.agenceloc.automobile.repositories;

import com.msr.agenceloc.automobile.subclasse.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    @Query(value = "SELECT SUM(`prix_journalier`) FROM vehicule WHERE 1;", nativeQuery = true)
    Optional<Integer> findTotalPrixVehicule();

    @Query(value ="SELECT COUNT(*) FROM  vehicule WHERE is_booked=1;", nativeQuery = true)
    int  findAllByReserver(boolean isBooked);

}
