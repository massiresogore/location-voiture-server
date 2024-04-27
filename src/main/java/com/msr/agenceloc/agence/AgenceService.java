package com.msr.agenceloc.agence;

import com.msr.agenceloc.agenceInformationDto.AgenceInformationDto;
import com.msr.agenceloc.automobile.repositories.AutomobilRepository;
import com.msr.agenceloc.automobile.repositories.CamionRepository;
import com.msr.agenceloc.automobile.repositories.ScooterRepository;
import com.msr.agenceloc.automobile.repositories.VoitureRepository;
import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.client.ClientUserRepository;
import com.msr.agenceloc.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class AgenceService {
    private final ScooterRepository scooterRepository;
    private final VoitureRepository voitureRepository;
    private final CamionRepository camionRepository;
    private final AgenceRepository agenceRepository;
    private final AutomobilRepository automobilRepository;
    private final ClientUserRepository clientUserRepository;

    public AgenceService(ScooterRepository scooterRepository, VoitureRepository voitureRepository, CamionRepository camionRepository, AgenceRepository agenceRepository, AutomobilRepository automobilRepository, ClientUserRepository clientUserRepository) {
        this.scooterRepository = scooterRepository;
        this.voitureRepository = voitureRepository;
        this.camionRepository = camionRepository;
        this.agenceRepository = agenceRepository;
        this.automobilRepository = automobilRepository;
        this.clientUserRepository = clientUserRepository;
    }

    /***
     * <p> renvoie l'gence sauvegardé</p>
     *
     * @return la liste des  agences
     * @see List<Agence>
     */
    public List<Agence> getAllAgence() {
        return this.agenceRepository.findAll();
    }

    /**
     * <p>Prend en parametre agenceId</p>
     *
     * <p>le sauvegarde et renvoie le Agence sauvegardé</p>
     *
     * @throws ObjectNotFoundException renvoie une
     * exception si user n'existe pas
     *
     * @param agenceId  non null ( agenceId est une Long )
     * @return l'objet Agence
     * @see Agence
     */
    public Agence findById(Long agenceId) {
        return this.agenceRepository.findById(agenceId).orElseThrow(()->new ObjectNotFoundException("agence",agenceId));
    }

    /**
     * <p>Prend en parametre agenceId</p>
     *
     * <p>vérifie si agence existe si oui il supprime</p>
     *
     * @throws ObjectNotFoundException renvoie une
     * exception si agence n'existe pas
     *
     * @param agenceId  non null ( agenceId est une Long )
     */
    public void delete(Long agenceId) {
        this.agenceRepository.findById(agenceId).orElseThrow(()->new ObjectNotFoundException("agence",agenceId));
        this.agenceRepository.deleteById(agenceId);
    }

    /**
     * <p>Prend en parametre un objet agence user</p>
     *
     * <p>le sauvegarde et revoie l'gence sauvegarder</p>
     *
     * @param agence ( agence est une entité )
     * @return l'objet Agence
     * @see Agence
     */
    public Agence save(Agence agence) {
        return this.agenceRepository.save(agence);
    }


    /**
     * <p>Prend en parametre update</p>
     *
     * <p>le sauvegarde et renvoie  l'agence sauvegardé</p>
     *
     * @throws ObjectNotFoundException renvoie une
     * exception si agence n'existe pas
     *
     * @param agenceId  non null ( update est une Long )
     * @param update  non null
     * ( update est une object à mettre à jour )
     *
     * @return l'objet update modifié
     * @see ClientUser
     */
    public Agence update(Long agenceId, Agence update) {

     return    this.agenceRepository.findById(agenceId).map(oldAgence->{
            oldAgence.setNom(update.getNom());
            oldAgence.setAdresse(update.getAdresse());
            oldAgence.setEmail(update.getEmail());
            oldAgence.setTel(update.getTel());
            return this.agenceRepository.save(oldAgence);
        }).orElseThrow(()->new ObjectNotFoundException("agence",agenceId));
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
        long totalAutomobile = this.automobilRepository.count();


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
