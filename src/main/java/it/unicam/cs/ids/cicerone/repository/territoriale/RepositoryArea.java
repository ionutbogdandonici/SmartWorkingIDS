package it.unicam.cs.ids.cicerone.repository.territoriale;

import it.unicam.cs.ids.cicerone.model.territoriale.Area;
import it.unicam.cs.ids.cicerone.model.territoriale.Territorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryArea extends JpaRepository<Area, Long> {
    /**
     * Restituisce l'area con il nome specificato
     * @param toponimo nome dell'area
     * @return  area con il nome specificato
     */
     Area findByToponimo(String toponimo);

     List<Area> findByTerritorio(Territorio territorio);

}
