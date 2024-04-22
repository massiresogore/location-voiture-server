package com.msr.agenceloc.automobile.repositories;

import com.msr.agenceloc.automobile.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomobilRepository extends JpaRepository<Automobile, Long> {

    List<Automobile> findAllByIsBooked(boolean booked);


    //@Query(value = "select COUNT(*) from vehicule as v INNER JOIN agence as a on v.agence_id=a.agence_id WHERE v.is_booked =1;", nativeQuery = false)

    /*@Query(value = "SELECT c.*, o.*, p.* "
            + " from Customer c, CustomerOrder o ,Product p "
            + " where c.id=o.customer_id "
            + " and o.id=p.customerOrder_id "
            + " and c.id=?1 "
            , nativeQuery = true)
    List<Map<String, Object>> findByCustomer(Long id);
*/
    @Query(value = "select COUNT(*) from reservation as r" +
            " JOIN automobile as aut " +
            "on r.automobile_id = aut.agence_id " +
            "join `agence` as a" +
            " on a.agence_id=aut.agence_id " +
            "WHERE a.agence_id=?1;",nativeQuery = true)
    int findReservationByAgenceId(int agence_id);

}
