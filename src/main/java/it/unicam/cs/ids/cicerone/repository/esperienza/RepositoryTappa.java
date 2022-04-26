package it.unicam.cs.ids.cicerone.repository.esperienza;

import it.unicam.cs.ids.cicerone.model.esperienza.Tappa;
import it.unicam.cs.ids.cicerone.model.territoriale.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryTappa extends JpaRepository<Tappa, Long> {

    /**
     * Restituisce tutte le tappe appartenenti all'area passata come parametro
     *
     * @param area area di cui si vogliono ottenere le tappe
     * @return lista di tappe appartenenti all'area
     */
    List<Tappa> findByAreaTappa(Area area);

    /**
     * Restituisce la tappa corrispondente al Nome, Descrizione e Raggiunta
     *
     * @param nome        nome della tappa
     * @param descrizione descrizione della tappa
     * @param raggiunta   raggiunta della tappa
     * @return tappa
     */
    Tappa findByNomeAndDescrizioneAndRaggiunta(String nome, String descrizione, String raggiunta);

}
