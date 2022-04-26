package it.unicam.cs.ids.cicerone.repository.territoriale;

import it.unicam.cs.ids.cicerone.model.territoriale.Regione;
import it.unicam.cs.ids.cicerone.model.territoriale.Territorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryTerritorio extends JpaRepository<Territorio, Long> {

    /**
     * Restituisce la lista di tutti i territori appartenenti alla regione specificata.
     * @param regione la regione di cui si vogliono i territori
     * @return la lista di territori appartenenti alla regione specificata
     */
    List<Territorio> findByRegione(Regione regione);

    /**
     * Restituisce il territorio con il nome specificato.
     * @param nome il nome del territorio
     * @return il territorio con il nome specificato
     */
    Territorio findByNome(String nome);
}
