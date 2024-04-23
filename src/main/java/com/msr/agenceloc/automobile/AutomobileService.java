package com.msr.agenceloc.automobile;

import com.msr.agenceloc.agence.Agence;
import com.msr.agenceloc.automobile.dto.AutomobileDto;
import com.msr.agenceloc.automobile.repositories.AutomobilRepository;
import com.msr.agenceloc.automobile.subclasse.Camion;
import com.msr.agenceloc.automobile.subclasse.Scooter;
import com.msr.agenceloc.automobile.subclasse.Voiture;
import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.client.ClientUserService;
import com.msr.agenceloc.image.StorageService;
import com.msr.agenceloc.reservation.Reservation;
import com.msr.agenceloc.reservation.ReservationService;
import com.msr.agenceloc.reservation.dto.ReservationDto;
import com.msr.agenceloc.system.exception.ObjectNotFoundException;
import com.msr.agenceloc.system.exception.ReservationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AutomobileService {
    private final StorageService service;
    private final AutomobilRepository automobilRepository;
    private final ClientUserService clientUserService;
    private final ReservationService reservationService;

    public AutomobileService(StorageService service,
                             AutomobilRepository automobilRepository,
                             ClientUserService clientUserService,
                             ReservationService reservationService) {
        this.service = service;
        this.automobilRepository = automobilRepository;
        this.clientUserService = clientUserService;
        this.reservationService = reservationService;
    }

    public boolean reserver(String clientId,
                            String automobileId,
                            ReservationDto reservationDto
                            )
    {
        //Covertir toute les date
       try {
           LocalDate dateDebut = this.stringToLocalDate(reservationDto.dateDebut());
           LocalDate dateFin = this.stringToLocalDate(reservationDto.dateFin());

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
           this.reservationService.reserver(reservation);
           return true;
       }catch (ReservationException e){

           return false;
       }

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

    public Camion createCamion(AutomobileDto automobile, Agence agence)
    {
        return  this.automobilRepository.save(this.getCamion(automobile,agence));
    }
    public Scooter createScooter(AutomobileDto automobile, Agence agence)
    {
        return  this.automobilRepository.save(this.getScooter(automobile,agence));
    }

    public Voiture createVoiture(AutomobileDto automobile, Agence agence)
    {
        return this.automobilRepository.save(this.getVoiture( automobile,agence));
    }

    private Voiture getVoiture(AutomobileDto automobile, Agence agence){
        return new Voiture(
                null,
                automobile.couleur(),
                automobile.poids(),
                automobile.prixJournalier(),
                automobile.isBooked(),
                automobile.stock(),
                agence,
                automobile.nbRoues(),
                automobile.nbrPorte()
        );
    }

    private Scooter getScooter(AutomobileDto automobile, Agence agence){
        return  new Scooter(
                null,
                automobile.couleur(),
                automobile.poids(),
                automobile.prixJournalier(),
                automobile.isBooked(),
                automobile.stock(),
                agence,
                automobile.cylindre()
        );
    }

    private Camion getCamion(AutomobileDto automobile, Agence agence){
        return new Camion(
                null,
                automobile.couleur(),
                automobile.poids(),
                automobile.prixJournalier(),
                automobile.isBooked(),
                automobile.stock(),
                agence,
                automobile.longueur()
        );
    }


}
