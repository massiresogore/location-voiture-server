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


	}

/*
	@Bean
	 public CommandLineRunner addVeh(
			 AgenceRepository agenceRepository,
			 AutomobilRepository automobilRepository
									 ){
		 Agence afrimeta = agenceRepository.findById(Long.parseLong(String.valueOf(1))).get();

		 //créé véhicule




		*/
/*Automobile veicule = new Vehicule(
				null,
				"rouge",
				20,
				46,
				false,

		);
		Automobile vehicule = new Vehicule(
				null,
				automobile.couleur(),
				automobile.poids(),
				automobile.prixJournalier(),
				automobile.isBooked(),
				client,
				automobile.nbRoues(),
				automobile.nbrPorte()
		);*//*


		return runner ->{
			System.out.println();

		};
	 }
*/

}
