package com.msr.agenceloc.automobile.repositories;

import com.msr.agenceloc.automobile.subclasse.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {
    @Query(value = "SELECT SUM(`prix_journalier`) FROM scooter WHERE 1;", nativeQuery = true)
    int findTotalPrixScooter();
}
