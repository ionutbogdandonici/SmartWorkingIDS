package it.unicam.cs.ids.cicerone.repository.esperienza;

import it.unicam.cs.ids.cicerone.model.esperienza.Esperienza;
import it.unicam.cs.ids.cicerone.model.territoriale.Area;
import it.unicam.cs.ids.cicerone.model.users.Associazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryEsperienza extends JpaRepository<Esperienza, Long> {


    public List<Esperienza> findByArea(Area area);

}
