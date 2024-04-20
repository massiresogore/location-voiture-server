package com.msr.agenceloc.client;

import com.msr.agenceloc.security.MyUserPrincipal;
import com.msr.agenceloc.system.exception.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientUserService implements UserDetailsService {

    private final ClientUserRepository clientUserRepository;

    private final PasswordEncoder passwordEncoder;


    public ClientUserService(ClientUserRepository clientUserRepository, PasswordEncoder passwordEncoder) {
        this.clientUserRepository = clientUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * <p>Prend en parametre un objet client user</p>
     *
     * <p>le sauvegarde et revoie clientUser sauvegarder</p>
     *
     * @param clientUser ( clientUser est une entité )
     * @return l'objet ClientUser
     * @see ClientUser
     */
    public ClientUser save(ClientUser clientUser)
    {
        clientUser.setPassword(this.passwordEncoder.encode(clientUser.getPassword()));

        return this.clientUserRepository.save(clientUser);
    }

    /**
     * <p>Prend en parametre userId</p>
     *
     * <p>le sauvegarde et renvoie le clientUser sauvegardé</p>
     *
     * @throws ObjectNotFoundException renvoie une
     * exception si user n'existe pas
     *
     * @param userId  non null ( userId est une Long )
     * @return l'objet ClientUser
     * @see ClientUser
     */
    public ClientUser findById(Long userId)
    {
        return this.clientUserRepository.findById(userId)
                .orElseThrow(()->new ObjectNotFoundException("user",userId));
    }

    /**
     * <p>Prend en parametre userId</p>
     *
     * <p>le sauvegarde et renvoie le clientUser sauvegardé</p>
     *
     * @throws ObjectNotFoundException renvoie une
     * exception si user n'existe pas
     *
     * @param userId  non null ( userId est une Long )
     * @param updatedUser  non null
     * ( updatedUser est une object à mettre à jour )
     *
     * @return l'objet ClientUser modifier
     * @see ClientUser
     */
    public ClientUser updateUser(Long userId,ClientUser updatedUser)
    {
       return   this.clientUserRepository.findById(userId)
                .map(oldUser->{
                    oldUser.setNom(updatedUser.getNom());
                    oldUser.setPrenom(updatedUser.getPrenom());
                    oldUser.setRoles(updatedUser.getRoles());
                    oldUser.setEmail(updatedUser.getEmail());
                    oldUser.setPassword(updatedUser.getPassword());
                    oldUser.setAdresse(updatedUser.getAdresse());
                  return   this.clientUserRepository.save(oldUser);
                }).orElseThrow(()->new ObjectNotFoundException("user",userId));

    }

    /***
     * <p> renvoie le clientUser sauvegardé</p>
     *
     * @return la liste des  ClientUsers
     * @see List<ClientUser>
     */
    public List<ClientUser> findAll()
    {
        return new ArrayList<>(this.clientUserRepository.findAll());
    }



    /**
     * <p>Prend en parametre userId</p>
     *
     * <p>vérifie si user existe si oui il supprime</p>
     *
     * @throws ObjectNotFoundException renvoie une
     * exception si user n'existe pas
     *
     * @param userId  non null ( userId est une Long )
     */
    public void delete(Long userId)
    {
        this.clientUserRepository.findById(userId)
               .orElseThrow(()->new ObjectNotFoundException("user",userId));

       this.clientUserRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return   this.clientUserRepository.findByNom(username)
                .map(clientUser -> new MyUserPrincipal(clientUser))
                .orElseThrow(()-> new UsernameNotFoundException("username "+username+" is not found"));
    }
}
