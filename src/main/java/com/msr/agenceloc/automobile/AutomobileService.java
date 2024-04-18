package com.msr.agenceloc.automobile;

import com.msr.agenceloc.agence.AgenceRepository;
import com.msr.agenceloc.automobile.repositories.AutomobilRepository;
import com.msr.agenceloc.automobile.repositories.CamionRepository;
import com.msr.agenceloc.automobile.repositories.ScooterRepository;
import com.msr.agenceloc.automobile.repositories.VehiculeRepository;
import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.client.ClientUserService;
import com.msr.agenceloc.date.DateReservation;
import com.msr.agenceloc.date.DateReservationRepository;
import com.msr.agenceloc.date.DateReservationService;
import com.msr.agenceloc.embeddable.ClientReserveVehicule;
import com.msr.agenceloc.embeddable.ClientReserveVehiculeKey;
import com.msr.agenceloc.embeddable.ReservationRepository;
import com.msr.agenceloc.embeddable.ReservationService;
import com.msr.agenceloc.embeddable.dto.ReservationDto;
import com.msr.agenceloc.image.StorageService;
import com.msr.agenceloc.system.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AutomobileService {
    private final StorageService service;
    private final AutomobilRepository automobilRepository;
    private final ClientUserService clientUserService;
    private final ScooterRepository scooterRepository;
    private final VehiculeRepository vehiculeRepository;
    private final CamionRepository camionRepository;
    private final AgenceRepository agenceRepository;
    private final ReservationRepository reservationRepository;
    private final DateReservationRepository dateReservationRepository;
    private final DateReservationService dateReservationService;
    private final ReservationService reservationService;

    public AutomobileService(StorageService service, AutomobilRepository automobilRepository, ClientUserService clientUserService, ScooterRepository scooterRepository, VehiculeRepository vehiculeRepository, CamionRepository camionRepository, AgenceRepository agenceRepository, ReservationRepository reservationRepository, DateReservationRepository dateReservationRepository, DateReservationService dateReservationService, ReservationService reservationService) {
        this.service = service;
        this.automobilRepository = automobilRepository;
        this.clientUserService = clientUserService;
        this.scooterRepository = scooterRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.camionRepository = camionRepository;
        this.agenceRepository = agenceRepository;
        this.reservationRepository = reservationRepository;
        this.dateReservationRepository = dateReservationRepository;
        this.dateReservationService = dateReservationService;
        this.reservationService = reservationService;
    }



    public boolean reserver(String clientId,
                            String automobileId,
                            ReservationDto reservationDto
                            )
    {
        //Covertir toute les date
        LocalDate dateDebut = this.stringToLocalDate(reservationDto.dateDebut());
        LocalDate dateFin = this.stringToLocalDate(reservationDto.dateFin());


        //Réservation (User et véhicule et date de réservation)

        //User
        ClientUser eyenga = this.clientUserService.findById(Long.parseLong(clientId));

        //Véhicule
        Automobile vehicule = this.findOneAutomobileById(Long.parseLong(automobileId));

        //Date
        DateReservation dateReservation = new DateReservation(dateDebut);
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
                dateDebut,
                dateFin,
                reservationDto.nombreJour(),
                reservationDto.prixJournalier()
        );

        vehicule.setBooked(true);
        this.automobilRepository.save(vehicule);
        this.reservationService.reserver(clientReserveVehicule);


        return true;
    }

    private LocalDate stringToLocalDate(String date)
    {
        return LocalDate.parse(date);
    }

        public Automobile findOneAutomobileById(Long automobileId)
    {
        return  this.automobilRepository.findById(automobileId).orElseThrow(()->new ObjectNotFoundException("vehicule",automobileId));
    }
}
