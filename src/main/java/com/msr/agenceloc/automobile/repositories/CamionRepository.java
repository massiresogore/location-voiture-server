package com.msr.agenceloc.automobile.repositories;

import com.msr.agenceloc.automobile.subclasse.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {
    @Query(value = "SELECT SUM(`prix_journalier`) FROM camion WHERE 1;", nativeQuery = true)
    Optional<Integer> findTotalPrixCamion();



    @Query(value ="SELECT COUNT(*) FROM  camion WHERE is_booked=1;", nativeQuery = true)
    int findAllByReserver(boolean isBooked);
}
