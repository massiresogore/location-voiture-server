package com.msr.agenceloc.automobile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomobilRepository extends JpaRepository<Automobile, Long> {
}
