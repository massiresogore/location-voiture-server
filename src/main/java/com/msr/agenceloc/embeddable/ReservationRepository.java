package com.msr.agenceloc.embeddable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ClientReserveVehicule,ClientReserveVehiculeKey> {


}
