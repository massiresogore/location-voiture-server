package com.msr.agenceloc.automobile;

import com.msr.agenceloc.automobile.dto.AutomobileDto;
import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.client.ClientUserRepository;
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

@Controller
@RestController
@RequestMapping("/${api.endpoint.base-url}/automobile")
public class AutomobileController {
    private final StorageService service;
    private final AutomobilRepository automobilRepository;
    private final ClientUserRepository clientUserRepository;

    public AutomobileController(StorageService service,
                                AutomobilRepository automobilRepository,
                                ClientUserRepository clientUserRepository) {
        this.service = service;
        this.automobilRepository = automobilRepository;
        this.clientUserRepository = clientUserRepository;
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


    @PostMapping
    public Result addVehicule(@Valid @RequestBody AutomobileDto automobile)
    {
      //Retrive Client
        ClientUser client = clientUserRepository.findById(automobile.clientId())
                .orElseThrow(()-> new  ObjectNotFoundException("client",automobile.clientId()));

        //create scooter
        Result scooterResult = this.scooterResponse(automobile, client);
        if (scooterResult != null) return scooterResult;

        //Create Camion
        Result camionResult = this.camionResponse(automobile,client);
        if(camionResult!= null) return camionResult;

        //create vehicule
        return this.vehiculeResult(automobile, client);

    }

    private Result vehiculeResult(AutomobileDto automobile, ClientUser client) {
        Automobile vehicule = new Vehicule(
                null,
                automobile.couleur(),
                automobile.poids(),
                automobile.prixJournalier(),
                automobile.isBooked(),
                client,
                automobile.nbRoues(),
                automobile.nbrPorte()
        );
        Vehicule savedVehicule = (Vehicule) this.automobilRepository.save(vehicule);
        return new Result(true, StatusCode.SUCCESS, "Create vehicule success",
                savedVehicule
        );
    }

    private Result scooterResponse(AutomobileDto automobile, ClientUser client) {
        if(automobile.cylindre() != 0){

            Automobile scooter = new Scooter(
                    null,
                    automobile.couleur(),
                    automobile.poids(),
                    automobile.prixJournalier(),
                    automobile.isBooked(),
                    client,
                    automobile.cylindre()
            );

            Scooter savedScooter = (Scooter) this.automobilRepository.save(scooter);
            return new Result(true, StatusCode.SUCCESS, "Create vehicule success",
                    savedScooter
            );
        }
        return null;
    }

    private Result camionResponse(AutomobileDto automobile, ClientUser client) {
        if(automobile.longueur() != 0){

            Automobile camion = new Camion(
                    null,
                    automobile.couleur(),
                    automobile.poids(),
                    automobile.prixJournalier(),
                    automobile.isBooked(),
                    client,
                    automobile.longueur()
            );

            Camion savedCamion = (Camion) this.automobilRepository.save(camion);
            return new Result(true, StatusCode.SUCCESS, "Create camion success",
                    savedCamion
            );
        }
        return null;
    }


}

