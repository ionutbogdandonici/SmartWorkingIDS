package it.unicam.cs.ids.cicerone.repository.territoriale;

import it.unicam.cs.ids.cicerone.model.territoriale.Regione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryRegione extends JpaRepository<Regione, Long> {

    /**
     * Restituisce la regione con il nome specificato.
     *
     * @param nome il nome della regione
     * @return la regione con il nome specificato
     */
    public Regione findByNome(String nome);


}
