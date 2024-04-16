package com.msr.agenceloc.automobile.repositories;

import com.msr.agenceloc.automobile.subclasse.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {
    @Query(value = "SELECT SUM(`prix_journalier`) FROM camion WHERE 1;", nativeQuery = true)
    int findTotalPrixCamion();
}
