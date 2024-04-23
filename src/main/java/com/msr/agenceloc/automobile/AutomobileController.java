package com.msr.agenceloc.automobile;

import com.msr.agenceloc.agence.Agence;
import com.msr.agenceloc.agence.AgenceRepository;
import com.msr.agenceloc.agenceInformationDto.AgenceInformationDto;
import com.msr.agenceloc.automobile.dto.AutomobileDto;
import com.msr.agenceloc.automobile.repositories.AutomobilRepository;
import com.msr.agenceloc.automobile.repositories.CamionRepository;
import com.msr.agenceloc.automobile.repositories.ScooterRepository;
import com.msr.agenceloc.automobile.repositories.VoitureRepository;
import com.msr.agenceloc.automobile.subclasse.Camion;
import com.msr.agenceloc.automobile.subclasse.Scooter;
import com.msr.agenceloc.automobile.subclasse.Voiture;
import com.msr.agenceloc.client.ClientUserRepository;
import com.msr.agenceloc.image.StorageService;
import com.msr.agenceloc.reservation.Reservation;
import com.msr.agenceloc.reservation.ReservationRepository;
import com.msr.agenceloc.reservation.ReservationService;
import com.msr.agenceloc.reservation.dto.ReservationDto;
import com.msr.agenceloc.reservation.dtoResponse.ReservationResponseDto;
import com.msr.agenceloc.system.Result;
import com.msr.agenceloc.system.StatusCode;
import com.msr.agenceloc.system.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping("/${api.endpoint.base-url}/automobiles")
public class AutomobileController {
    private final StorageService service;
    private final AutomobilRepository automobilRepository;
    private final ClientUserRepository clientUserRepository;
    private final ScooterRepository scooterRepository;
    private final VoitureRepository voitureRepository;
    private final CamionRepository camionRepository;
    private final AgenceRepository agenceRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;
    private final AutomobileService automobileService;

    public AutomobileController(StorageService service, AutomobilRepository automobilRepository, ClientUserRepository clientUserRepository, ScooterRepository scooterRepository, VoitureRepository voitureRepository, CamionRepository camionRepository, AgenceRepository agenceRepository, ReservationRepository reservationRepository, ReservationService reservationService, AutomobileService automobileService) {
        this.service = service;
        this.automobilRepository = automobilRepository;
        this.clientUserRepository = clientUserRepository;
        this.scooterRepository = scooterRepository;
        this.voitureRepository = voitureRepository;
        this.camionRepository = camionRepository;
        this.agenceRepository = agenceRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
        this.automobileService = automobileService;
    }


    @GetMapping
    public Result getAllAuto()
    {

        return new Result(true,StatusCode.SUCCESS,"All auto",
                this.automobilRepository.findAll());
    }

    @PostMapping("/{automobileId}/image")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file,
                                                     @PathVariable("automobileId") Long automobileId) throws IOException {

        String uploadImage = service.uploadImageToFileSystem(file, automobileId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }


    @GetMapping("/image/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData=service.downloadImageFromFileSystem(fileName);
        String base64Photo = Base64.encodeBase64String(imageData);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(base64Photo);
    }

    @GetMapping("/{automobileId}/images")
    public ResponseEntity<?> imagesByAutomobile(@PathVariable Long automobileId) throws IOException {
        com.msr.agenceloc.automobile.Automobile automobile = this.automobilRepository.getReferenceById(automobileId);

        List<byte[]> images = service.downloadImagesFromFileSystem(automobile);
        List<String> base64Photos = new ArrayList<>();

        for(byte[] bites: images){
            String base64Photo = Base64.encodeBase64String(bites);
            base64Photos.add(base64Photo);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(base64Photos);
    }

    @GetMapping("/vehicules")
    public Result getVehicules()
    {
        return new Result(true, StatusCode.SUCCESS, "all vehicule success",
                this.voitureRepository.findAll()
        );
    }
    @GetMapping("/scooters")
    public Result getScooters()
    {
        return new Result(true, StatusCode.SUCCESS, "all scooter success",
                this.scooterRepository.findAll()
        );
    }
    @GetMapping("/camions")
    public Result getCamions()
    {

        return new Result(true, StatusCode.SUCCESS, "all camion success",
                this.camionRepository.findAll()
        );
    }

    @GetMapping("/reservations")
    public Result getReservations()
    {
        // a refaire
        List<Reservation> reservations = this.reservationRepository.findAll();

        List<ReservationResponseDto> reservationResponseDtoList = new ArrayList<>();
        for (Reservation reservation : reservations){
            ReservationResponseDto reservationDto = new ReservationResponseDto(
                    reservation.getClientUser().getClientUserId(),
                    reservation.getClientUser().getNom(),
                    reservation.getAutomobile().getId(),
                    reservation.getDateDebut(),
                    reservation.getDateFin(),
                    reservation.getAutomobile().prixJournalier,
                    reservation.getAutomobile().getClassName(),
                    reservation.getAutomobile().getAgence().getNom()
            );

            reservationResponseDtoList.add(reservationDto);
        }
        //System.out.println(reservations);



        return new Result(true, StatusCode.SUCCESS, "all reservation success",
                reservationResponseDtoList
        );
    }


    @PostMapping
    public Result addVehicule(@Valid @RequestBody AutomobileDto automobile)
    {


        Agence agence = agenceRepository.findById(automobile.agenceId()).orElseThrow(
                ()-> new ObjectNotFoundException("agence", automobile.agenceId())
        );

        //create scooter
        Result scooterResult = this.scooterResponse(automobile, agence);
        if (scooterResult != null) return scooterResult;

        //Create Camion
        Result camionResult = this.camionResponse(automobile, agence);
        if(camionResult!= null) return camionResult;

        //create vehicule
        return this.vehiculeResult(automobile, agence);

    }

    @GetMapping("/information")
    public Result information()
    {

        return  new Result(
                true,
                StatusCode.SUCCESS,
                "Toutes les informations de l'application ",
               this.getInfoProgramme()
        );
    }

    @PostMapping("/{automobileId}/reservation/{clientId}")
    public Result reserver(@PathVariable("clientId") String  clientId,
                           @PathVariable("automobileId") String automobileId,
                           @RequestBody ReservationDto reservationDto
                           )
    {

       // System.out.println(reservationDto);
        this.automobileService.reserver(clientId,automobileId,reservationDto);

        return new Result(true,StatusCode.SUCCESS,"réservation réussit");
    }


    private Result vehiculeResult(AutomobileDto automobile, Agence agence) {
        com.msr.agenceloc.automobile.Automobile vehicule = new Voiture(
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
        Voiture savedVoiture = (Voiture) this.automobilRepository.save(vehicule);
        return new Result(true, StatusCode.SUCCESS, "Create vehicule success",
                savedVoiture
        );
    }



    private Result scooterResponse(AutomobileDto automobile,Agence agence) {
        if(automobile.cylindre() != 0){

            com.msr.agenceloc.automobile.Automobile scooter = new Scooter(
                    null,
                    automobile.couleur(),
                    automobile.poids(),
                    automobile.prixJournalier(),
                    automobile.isBooked(),
                    automobile.stock(),
                    agence,
                    automobile.cylindre()
            );

            Scooter savedScooter = (Scooter) this.automobilRepository.save(scooter);
            return new Result(true, StatusCode.SUCCESS, "Create scooter success",
                    savedScooter
            );
        }
        return null;
    }

    private Result camionResponse(AutomobileDto automobile, Agence agence) {
        if(automobile.longueur() != 0 ){

            com.msr.agenceloc.automobile.Automobile camion = new Camion(
                    null,
                    automobile.couleur(),
                    automobile.poids(),
                    automobile.prixJournalier(),
                    automobile.isBooked(),
                    automobile.stock(),
                    agence,
                    automobile.longueur()
            );

            Camion savedCamion = (Camion) this.automobilRepository.save(camion);
            return new Result(true, StatusCode.SUCCESS, "Create camion success",
                    savedCamion
            );
        }
        return null;
    }


    public AgenceInformationDto getInfoProgramme()
    {
        //total Véhicule
        long totalVehicule = this.voitureRepository.count();

        //total Camions
        long totalCamion = this.camionRepository.count();

        //total Scooter
        long totalScooter = this.scooterRepository.count();

        //total Agence
        long totalAgence = this.agenceRepository.count();

        //Total client
        int totalClient = this.clientUserRepository.findAll().size();


        //total reservation
        long totalReservation= this.automobilRepository.findAllByIsBooked(true).size();
         long totalAutomobile = this.automobileService.count();

        //total prix voiture,camion résevé,
        //Optional<Integer> totalPrixVehiculeAndCamionReserve = this.reservationRepository.findTotalPrixVehiculeAndCamionReserve();
//        int totalPrixVehiculeAndCamionReserveV= 0;
//        if (totalPrixVehiculeAndCamionReserve.isPresent()){
//            totalPrixVehiculeAndCamionReserveV=totalPrixVehiculeAndCamionReserve.get();
//        }

/*

        //Total Prix journalier camion
        Optional<Integer>  totalPrixJournalierCamion = this.camionRepository.findTotalPrixCamion();
        int totalPrixJournalierCamionV=0;
        if (totalPrixJournalierCamion.isPresent()){
            totalPrixJournalierCamionV = totalPrixJournalierCamion.get();
        }
*/

        //Total prix  scooter réservé
       /* Optional<Integer>  totalPrixScooterDeuxRouesreserve = this.reservationRepository.totalPrixScooterReserve();
        int totalPrixScooterReserve=0;
        if (totalPrixScooterDeuxRouesreserve.isPresent()){
            totalPrixScooterReserve=totalPrixScooterDeuxRouesreserve.get();
        }*/

        //int totalPrixVoitureCamionQuatreRoues = totalPrixVehiculeAndCamionReserveV;

        /*Total général de réservation, deux roue et quatres roues*/
//        int totalGeneralDeuxRouesEtQuatreRoues = totalPrixVoitureCamionQuatreRoues + totalPrixScooterReserve;

        //Total Camion réservé
//        int totalCamionReserve = camionRepository.findAllByReserver(true);

        //PoucentageCamion
//        int pourcentageCamionReserver = ((totalCamionReserve * 100)/totalCamion);


        //Totalv Véhicule réservé
//        int totalVoitureReserve = this.voitureRepository.findAllByReserver(true);
        //PoucentageVehicule

//        int pourcentageVehiculeReserver = ((totalVoitureReserve * 100)/totalVehicule);


//        int totalScooterReserve =this.scooterRepository.findAllByReserver(true);
//        int pourcentageScooterReserver = ((totalScooterReserve * 100)/totalScooter);
//        /*
//           long totalAgence,
//        long totalVehicule,
//        long totalCamion,
//        long totalScooter,
//        long totalClient,
//        Long totalAutomobile,
//        long totalReservation*/

        return  new AgenceInformationDto(
                totalAgence,
                totalVehicule,
                totalCamion,
                totalScooter,
                totalClient,
                totalAutomobile,
                totalReservation
        );

    }


}


