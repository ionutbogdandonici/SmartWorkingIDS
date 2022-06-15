package it.unicam.cs.ids.cicerone.repository.utility;

import it.unicam.cs.ids.cicerone.model.users.Associazione;
import it.unicam.cs.ids.cicerone.model.users.Cicerino;
import it.unicam.cs.ids.cicerone.model.users.Gruppo;
import it.unicam.cs.ids.cicerone.model.utility.Richiesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryRichiesta extends JpaRepository<Richiesta, Long> {
    @Query(value = "SELECT * FROM richieste WHERE id_associazine = ?1", nativeQuery = true)
    public Richiesta findByAssociazione(Long idAssociazione);


}
