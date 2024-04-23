package com.msr.agenceloc.automobile;


import com.msr.agenceloc.automobile.repositories.VoitureRepository;
import com.msr.agenceloc.automobile.subclasse.Voiture;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VehiculeService {

    private final VoitureRepository voitureRepository;

    public VehiculeService(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;

    }

    public Voiture save(Voiture voiture) {
        return   this.voitureRepository.save(voiture);
    }

  /*  public List<Vehicule> findAllVehicule()
    {
        List<Vehicule> vehicules = new ArrayList<>();
        vehicules = this.vehiculeRepository.findAll().stream().toList();
        return vehicules;
    }*/

//    public Vehicule findOneVehiculeById(Long vehiculeId)
//    {
//        return  this.vehiculeRepository.findById(vehiculeId).orElseThrow(()->new ObjectNotFoundException("vehicule",vehiculeId));
//    }

  /*  public void deleteVehicule(Long vehiculeId) {
        Vehicule vehicule =  this.vehiculeRepository.findById(vehiculeId).orElseThrow(()->new ObjectNotFoundException("vehicule",vehiculeId));
        this.vehiculeRepository.delete(vehicule);
    }*/

//    public Vehicule updateVehicule(int vehiculeId, Vehicule update)
//    {
//        return   this.vehiculeRepository.findById(vehiculeId)
//                .map(oldVehicule->{
//                    oldVehicule.setDescription(update.getDescription());
//                    oldVehicule.setModele(update.getModele());
//                    oldVehicule.setImage(update.getImage());
//                    oldVehicule.setMarque(update.getMarque());
//                    oldVehicule.setPrix_journalier(update.getPrix_journalier());
//                    return this.vehiculeRepository.save(oldVehicule);
//                })
//                .orElseThrow(()->new ObjectNotFoundException("vehicule",vehiculeId));
//    }
}
