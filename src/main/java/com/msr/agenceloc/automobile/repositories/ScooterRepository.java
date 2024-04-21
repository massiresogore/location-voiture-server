package com.msr.agenceloc.automobile.repositories;

import com.msr.agenceloc.automobile.subclasse.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {
    @Query(value = "SELECT SUM(`prix_journalier`) FROM scooter WHERE 1;", nativeQuery = true)
    Optional<Integer> findTotalPrixScooter();

    @Query(value ="SELECT COUNT(*) FROM  scooter WHERE is_booked=1;", nativeQuery = true)
    int findAllByReserver(boolean isBooked);

}
