package com.msr.agenceloc.automobile;

import com.msr.agenceloc.agence.AgenceRepository;
import com.msr.agenceloc.automobile.repositories.AutomobilRepository;
import com.msr.agenceloc.automobile.repositories.CamionRepository;
import com.msr.agenceloc.automobile.repositories.ScooterRepository;
import com.msr.agenceloc.automobile.repositories.VoitureRepository;
import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.client.ClientUserService;
import com.msr.agenceloc.image.StorageService;
import com.msr.agenceloc.reservation.Reservation;
import com.msr.agenceloc.reservation.ReservationRepository;
import com.msr.agenceloc.reservation.ReservationService;
import com.msr.agenceloc.reservation.dto.ReservationDto;
import com.msr.agenceloc.system.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AutomobileService {
    private final StorageService service;
    private final AutomobilRepository automobilRepository;
    private final ClientUserService clientUserService;
    private final ScooterRepository scooterRepository;
    private final VoitureRepository voitureRepository;
    private final CamionRepository camionRepository;
    private final AgenceRepository agenceRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public AutomobileService(StorageService service, AutomobilRepository automobilRepository, ClientUserService clientUserService, ScooterRepository scooterRepository, VoitureRepository voitureRepository, CamionRepository camionRepository, AgenceRepository agenceRepository, ReservationRepository reservationRepository, ReservationService reservationService) {
        this.service = service;
        this.automobilRepository = automobilRepository;
        this.clientUserService = clientUserService;
        this.scooterRepository = scooterRepository;
        this.voitureRepository = voitureRepository;
        this.camionRepository = camionRepository;
        this.agenceRepository = agenceRepository;
        this.reservationRepository = reservationRepository;
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





       //Reservation
        Reservation reservation = new Reservation(
                null,
                eyenga,
                vehicule,
                dateDebut,
                dateFin,
                reservationDto.prixJournalier()

        );

        vehicule.setBooked(true);
        this.automobilRepository.save(vehicule);
        this.reservationService.reserver(reservation);

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

    public long count(){
        return this.automobilRepository.count();
    }


}
