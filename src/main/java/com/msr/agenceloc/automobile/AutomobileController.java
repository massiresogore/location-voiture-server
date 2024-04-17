package com.msr.agenceloc.automobile;

import com.msr.agenceloc.agence.Agence;
import com.msr.agenceloc.agence.AgenceRepository;
import com.msr.agenceloc.agenceInformationDto.AgenceInformationDto;
import com.msr.agenceloc.automobile.dto.AutomobileDto;
import com.msr.agenceloc.automobile.repositories.AutomobilRepository;
import com.msr.agenceloc.automobile.repositories.CamionRepository;
import com.msr.agenceloc.automobile.repositories.ScooterRepository;
import com.msr.agenceloc.automobile.repositories.VehiculeRepository;
import com.msr.agenceloc.automobile.subclasse.Camion;
import com.msr.agenceloc.automobile.subclasse.Scooter;
import com.msr.agenceloc.automobile.subclasse.Vehicule;
import com.msr.agenceloc.client.ClientUserRepository;
import com.msr.agenceloc.embeddable.ReservationRepository;
import com.msr.agenceloc.image.StorageService;
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
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/${api.endpoint.base-url}/automobiles")
public class AutomobileController {
    private final StorageService service;
    private final AutomobilRepository automobilRepository;
    private final ClientUserRepository clientUserRepository;
    private final ScooterRepository scooterRepository;
    private final VehiculeRepository vehiculeRepository;
    private final CamionRepository camionRepository;
    private final AgenceRepository agenceRepository;
    private final ReservationRepository reservationRepository;

    public AutomobileController(StorageService service,
                                AutomobilRepository automobilRepository,
                                ClientUserRepository clientUserRepository, ScooterRepository scooterRepository, VehiculeRepository vehiculeRepository, CamionRepository camionRepository, AgenceRepository agenceRepository, ReservationRepository reservationRepository) {
        this.service = service;
        this.automobilRepository = automobilRepository;
        this.clientUserRepository = clientUserRepository;
        this.scooterRepository = scooterRepository;
        this.vehiculeRepository = vehiculeRepository;
        this.camionRepository = camionRepository;
        this.agenceRepository = agenceRepository;
        this.reservationRepository = reservationRepository;
    }


    @GetMapping
    public Result getAllAuto()
    {
       ;
        return new Result(true,StatusCode.SUCCESS,"All auto",this.automobilRepository.findAll());
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
        Automobile automobile = this.automobilRepository.getReferenceById(automobileId);

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
                this.vehiculeRepository.findAll()
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
        return new Result(true, StatusCode.SUCCESS, "all reservation success",
                this.reservationRepository.findAll()
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
                "Toutes les informations de l'application",
               this.getInfoProgramme()
        );
    }

    private Result vehiculeResult(AutomobileDto automobile, Agence agence) {
        Automobile vehicule = new Vehicule(
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
        Vehicule savedVehicule = (Vehicule) this.automobilRepository.save(vehicule);
        return new Result(true, StatusCode.SUCCESS, "Create vehicule success",
                savedVehicule
        );
    }

    private Result scooterResponse(AutomobileDto automobile,Agence agence) {
        if(automobile.cylindre() != 0){

            Automobile scooter = new Scooter(
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

            Automobile camion = new Camion(
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
         int totalVehicule = this.vehiculeRepository.findAll().size();

        //total Camions
         int totalCamion = this.camionRepository.findAll().size();

        //total Scooter
         int totalScooter = this.scooterRepository.findAll().size();

        //total Agence
         int totalAgence = this.agenceRepository.findAll().size();

        //Total client
         int totalClient = this.clientUserRepository.findAll().size();

        //total reservation
          List<Automobile> automobiles=  this.automobilRepository.findAllByIsBooked(true);
         int totalReservation = automobiles.size();

        //total priJournalier voiture,camion,
        Optional<Integer> totalPriJournalierVehicule = this.vehiculeRepository.findTotalPrixVehicule();
        int totalPriJournalierVehiculeV= 0;

        //Total Proux journalier camion
        Optional<Integer>  totalPrixJournalierCamion = this.camionRepository.findTotalPrixCamion();
        int totalPrixJournalierCamionV=0;

        //Total prix journalier scooter
        Optional<Integer>  totalPrixJournalerScooterDeuxRoues = this.scooterRepository.findTotalPrixScooter();
        int totalPrixJournalerScooterDeuxRouesV=0;

        if(totalPriJournalierVehicule.isPresent() || totalPrixJournalierCamion.isPresent() ||
                totalPrixJournalerScooterDeuxRoues.isPresent()
        ){
            totalPriJournalierVehiculeV = totalPriJournalierVehicule.get();
            totalPrixJournalierCamionV = totalPrixJournalierCamion.get();
            totalPrixJournalerScooterDeuxRouesV = totalPrixJournalerScooterDeuxRoues.get();

            int totalPrixVoitureCamionQuatreRoues = totalPriJournalierVehiculeV + totalPrixJournalierCamionV;

            /*Total général, deux roue et quatres roues*/
            int totalGeneralDeuxRouesEtQuatreRoues = totalPrixVoitureCamionQuatreRoues + totalPrixJournalerScooterDeuxRouesV;
            return  new AgenceInformationDto(
                    totalAgence,
                    totalVehicule,
                    totalCamion,
                    totalScooter,
                    totalClient,
                    totalReservation,
                    totalPrixVoitureCamionQuatreRoues,
                    totalPrixJournalerScooterDeuxRouesV,
                    totalGeneralDeuxRouesEtQuatreRoues
            );
        }

        return  new AgenceInformationDto(
                totalAgence,
                totalVehicule,
                totalCamion,
                totalScooter,
                totalClient,
                totalReservation,
                0,
                0,
                0
        );





    }
}


