package it.unicam.cs.ids.cicerone.repository.users;

import it.unicam.cs.ids.cicerone.model.users.Cicerino;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RepositoryCicerino extends JpaRepository<Cicerino,Long> {
    public Cicerino findByUsername(String username);
}
