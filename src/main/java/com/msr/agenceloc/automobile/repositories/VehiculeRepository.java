package com.msr.agenceloc.automobile.repositories;

import com.msr.agenceloc.automobile.subclasse.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    @Query(value = "SELECT SUM(`prix_journalier`) FROM vehicule WHERE 1;", nativeQuery = true)
    int findTotalPrixVehicule();
}
