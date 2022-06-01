package it.unicam.cs.ids.cicerone.autenticate.JWT_Service;

import it.unicam.cs.ids.cicerone.autenticate.JWT_Model.UsersDetails;
import it.unicam.cs.ids.cicerone.model.users.Associazione;
import it.unicam.cs.ids.cicerone.model.users.Cicerino;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryAdmin;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryAssociazione;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryCicerino;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryTurista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    RepositoryTurista repositoryTurista;

    @Autowired
    RepositoryCicerino repositoryCicerino;

    @Autowired
    RepositoryAssociazione repositoryAssociazione;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Turista turista = repositoryTurista.findByUsername(username);
        final Cicerino cicerino = repositoryCicerino.findByUsername(username);
        final Associazione associazione = repositoryAssociazione.findByUsername(username);
        if(turista != null){
            return new UsersDetails(turista);
        }else if(cicerino != null){
            return new UsersDetails(cicerino);
        }else if(associazione != null){
            return new UsersDetails(associazione);
        }else {
            throw new UsernameNotFoundException("Usern not found");
        }
    }
}
