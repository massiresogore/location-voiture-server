package com.msr.agenceloc.client;

import com.msr.agenceloc.adresse.Adresse;
import com.msr.agenceloc.adresse.AdresseRepository;
import com.msr.agenceloc.system.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientUserService {

    private final ClientUserRepository clientUserRepository;


    public ClientUserService(ClientUserRepository clientUserRepository) {
        this.clientUserRepository = clientUserRepository;
    }

    public ClientUser save(ClientUser clientUser)
    {

        return this.clientUserRepository.save(clientUser);
    }

    public ClientUser findById(Long userId)
    {
        return this.clientUserRepository.findById(userId)
                .orElseThrow(()->new ObjectNotFoundException("user",userId));
    }

    public ClientUser updateUser(Long userId,ClientUser updatedUser)
    {
       return   this.clientUserRepository.findById(userId)
                .map(oldUser->{
                    oldUser.setNom(updatedUser.getNom());
                    oldUser.setPrenom(updatedUser.getPrenom());
                    oldUser.setRole(updatedUser.getRole());
                    oldUser.setBooked(updatedUser.isBooked());
                    oldUser.setEmail(updatedUser.getEmail());
                    oldUser.setPassword(updatedUser.getPassword());
                    oldUser.setAdresse(updatedUser.getAdresse());
                  return   this.clientUserRepository.save(oldUser);
                }).orElseThrow(()->new ObjectNotFoundException("user",userId));

    }

    public List<ClientUser> findAll()
    {
        return new ArrayList<>(this.clientUserRepository.findAll());
    }

    public void delete(Long userId)
    {
        this.clientUserRepository.findById(userId)
               .orElseThrow(()->new ObjectNotFoundException("user",userId));

       this.clientUserRepository.deleteById(userId);
    }
}
