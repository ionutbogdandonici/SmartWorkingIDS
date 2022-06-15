package it.unicam.cs.ids.cicerone.repository.users;

import it.unicam.cs.ids.cicerone.model.users.Gruppo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepositoryGruppo extends JpaRepository<Gruppo,Long> {
    @Query(value = "SELECT * FROM gruppi WHERE id_associazione = ?1", nativeQuery = true)
    public Gruppo finByAssociazione(Long idAssociazione);
}
