package it.unicam.cs.ids.cicerone.repository.users;

import it.unicam.cs.ids.cicerone.model.users.Associazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAssociazione extends JpaRepository<Associazione,Long> {
    public Associazione findByUsername(String username);


}
