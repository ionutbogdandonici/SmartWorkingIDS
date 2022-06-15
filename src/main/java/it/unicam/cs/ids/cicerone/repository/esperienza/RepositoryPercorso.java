package it.unicam.cs.ids.cicerone.repository.esperienza;

import it.unicam.cs.ids.cicerone.model.esperienza.Percorso;
import it.unicam.cs.ids.cicerone.model.territoriale.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryPercorso extends JpaRepository<Percorso, Long> {

    /**
     * Restituisce tutti i percorsi che fanno parte di una determinata area
     *
     * @param area area di appartenenza
     * @return lista dei percorsi appartenenti all'area
     */
    List<Percorso> findByArea(Area area);

    Percorso findByNomeAndArea(String nome, Area area);
}
