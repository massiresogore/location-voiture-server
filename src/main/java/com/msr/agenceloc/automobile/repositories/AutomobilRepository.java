package com.msr.agenceloc.automobile.repositories;

import com.msr.agenceloc.automobile.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomobilRepository extends JpaRepository<Automobile, Long> {

    List<Automobile> findAllByIsBooked(boolean booked);
}
