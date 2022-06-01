package it.unicam.cs.ids.cicerone.repository.users;

import it.unicam.cs.ids.cicerone.model.users.Cicerino;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTurista extends JpaRepository<Turista,Long> {
    public Turista findByUsername(String username);
}
