package com.msr.agenceloc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgencelocApplication {


    public static void main(String[] args) {
		SpringApplication.run(AgencelocApplication.class, args);
/*
		Automobile scoo = new ScooterAbs("Jakarta","Bleue",12,"tria,gle");
		Automobile vehi = new VehiculeAbs("tesla","Noire",1200,7);

		//scoo.getObjevtName();;
		vehi.getObjevtName();*/
		//Totalv Véhicule réservé


	}





//	@Bean
//	 public CommandLineRunner addVeh(
//			 ClientUserRepository clientUserRepository,
//			 AutomobilRepository automobilRepository,
//			 DateReservationRepository dateReservationRepository,
//			 ReservationRepository reservationRepository,
//			 CamionRepository camionRepository
//
//									 ) {
//
//
//
//
//		return runner ->{
//			System.out.println("done");
//
//
//		};
//	}
//
//
}





/*//*
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
*//*
*/
