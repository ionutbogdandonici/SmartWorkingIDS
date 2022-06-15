package it.unicam.cs.ids.cicerone.repository.users;

import it.unicam.cs.ids.cicerone.model.users.Associazione;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAssociazione extends JpaRepository<Associazione,Long> {
    public Associazione findByUsername(String username);


}
