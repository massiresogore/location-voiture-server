package com.msr.agenceloc;

import com.msr.agenceloc.automobile.repositories.AutomobilRepository;
import com.msr.agenceloc.client.ClientUserRepository;
import com.msr.agenceloc.date.DateReservationRepository;
import com.msr.agenceloc.embeddable.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgencelocApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgencelocApplication.class, args);
/*
		Automobile scoo = new ScooterAbs("Jakarta","Bleue",12,"tria,gle");
		Automobile vehi = new VehiculeAbs("tesla","Noire",1200,7);

		//scoo.getObjevtName();;
		vehi.getObjevtName();*/


	}


	@Bean
	 public CommandLineRunner addVeh(
			 ClientUserRepository clientUserRepository,
			 AutomobilRepository automobilRepository,
			 DateReservationRepository dateReservationRepository,
			 ReservationRepository reservationRepository

									 ){










/*
		//Réservation (User et véhicule et date de réservation)
			//User
		ClientUser eyenga = clientUserRepository.findById(Long.parseLong("1")).get();

		//Véhicule
		Automobile vehicule = automobilRepository.findById(Long.parseLong("2")).get();
		System.out.println(vehicule);

		//Date
		DateReservation dateReservation = new DateReservation(LocalDate.now());
		dateReservationRepository.save(dateReservation);

		//Reserver maintenant
			//clé de réservation
				ClientReserveVehiculeKey clientReserveVehiculeKey = new ClientReserveVehiculeKey(
						eyenga.getClientUserId(),
						vehicule.getId(),
						dateReservation.getDateReservation()
				);

			//Reservation
		ClientReserveVehicule clientReserveVehicule = new ClientReserveVehicule(
				clientReserveVehiculeKey,
				eyenga,
				vehicule,
				dateReservation,
				LocalDate.now(),
				LocalDate.now()
		);
		System.out.println(vehicule);
		vehicule.setBooked(true);
		Automobile a = automobilRepository.save(vehicule);
		System.out.println(a);
		reservationRepository.save(clientReserveVehicule);
*/

		return runner ->{
			System.out.println("done");


		};
	 }


}
