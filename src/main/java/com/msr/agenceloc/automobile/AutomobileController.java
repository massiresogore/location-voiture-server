package com.msr.agenceloc.automobile;

import com.msr.agenceloc.agence.Agence;
import com.msr.agenceloc.agence.AgenceRepository;
import com.msr.agenceloc.agence.AgenceService;
import com.msr.agenceloc.agenceInformationDto.AgenceInformationDto;
import com.msr.agenceloc.automobile.dto.AutomobileDto;
import com.msr.agenceloc.automobile.repositories.AutomobilRepository;
import com.msr.agenceloc.automobile.repositories.CamionRepository;
import com.msr.agenceloc.automobile.repositories.ScooterRepository;
import com.msr.agenceloc.automobile.repositories.VoitureRepository;
import com.msr.agenceloc.client.ClientUserRepository;
import com.msr.agenceloc.image.StorageService;
import com.msr.agenceloc.reservation.Reservation;
import com.msr.agenceloc.reservation.ReservationRepository;
import com.msr.agenceloc.reservation.ReservationService;
import com.msr.agenceloc.reservation.dto.ReservationDto;
import com.msr.agenceloc.reservation.dtoResponse.ReservationResponseDto;
import com.msr.agenceloc.system.Result;
import com.msr.agenceloc.system.StatusCode;
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
    private final AgenceService agenceService;

    public AutomobileController(StorageService service, AutomobilRepository automobilRepository, ClientUserRepository clientUserRepository, ScooterRepository scooterRepository, VoitureRepository voitureRepository, CamionRepository camionRepository, AgenceRepository agenceRepository, ReservationRepository reservationRepository, ReservationService reservationService, AutomobileService automobileService, AgenceService agenceService) {
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
        this.agenceService = agenceService;
    }


    @GetMapping
    public Result getAllAuto(@RequestParam(name = "status", required = false) String status)
    {
        if(status != null){
            boolean isBook = status.equals("1");
            return new Result(true,StatusCode.SUCCESS,"All auto",
                    this.automobilRepository.findAllByIsBooked(isBook));
        }

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
        return new Result(true, StatusCode.SUCCESS, "all reservation success",
                reservationResponseDtoList
        );
    }


    @PostMapping
    public Result addVehicule(@Valid @RequestBody AutomobileDto automobile)
    {

        Agence agence =this.agenceService.findById(automobile.agenceId());

        //create scooter
        Result scooterResult = this.scooterResult(automobile, agence);
        if (scooterResult != null) return scooterResult;

        //Create Camion
        Result camionResult = this.camionResult(automobile, agence);
        if(camionResult!= null) return camionResult;

        //create voiture
        Result voitureResult = this.vehiculeResult(automobile, agence);
        if(voitureResult !=null) return  this.vehiculeResult(automobile, agence);

      return   new Result(false, StatusCode.INTERNAL_SERVER_ERREUR, "Something went wrong");
    }

    @GetMapping("/information")
    public Result information()
    {
        return  new Result(true,
                StatusCode.SUCCESS,
                "Toutes les informations de l'application ",
               this.agenceService.getInfoProgramme()
        );
    }

    @PostMapping("/{automobileId}/reservation/{clientId}")
    public Result reserver(@PathVariable("clientId") String  clientId,
                           @PathVariable("automobileId") String automobileId,
                           @RequestBody ReservationDto reservationDto
                           )
    {
        this.automobileService.reserver(clientId,automobileId,reservationDto);
        return new Result(true,StatusCode.SUCCESS,"réservation réussit");
    }


    private Result vehiculeResult(AutomobileDto automobile, Agence agence) {
       if(automobile.nbRoues() != 0 && automobile.nbrPorte() !=0 ){
           return new Result(true, StatusCode.SUCCESS, "Create vehicule success",
                   this.automobileService.createVoiture(automobile,agence)
           );
       }
        return  null;
    }

    private Result scooterResult(AutomobileDto automobile,Agence agence) {
        if(automobile.cylindre() != 0){
            return new Result(true, StatusCode.SUCCESS, "Create scooter success",
                    this.automobileService.createScooter(automobile,agence)
            );
        }
        return null;
    }

    private Result camionResult(AutomobileDto automobile, Agence agence) {

        System.out.println(automobile);
        if(automobile.longueur() != 0 ){
            return new Result(true, StatusCode.SUCCESS, "Create camion success",
                    this.automobileService.createCamion(automobile,agence)
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


