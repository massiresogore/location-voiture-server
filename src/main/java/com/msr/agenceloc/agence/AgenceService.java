package com.msr.agenceloc.agence;

import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AgenceService {
    private final AgenceRepository agenceRepository;

    public AgenceService(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
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
}
