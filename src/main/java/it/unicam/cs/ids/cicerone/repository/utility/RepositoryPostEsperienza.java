package it.unicam.cs.ids.cicerone.repository.utility;

import it.unicam.cs.ids.cicerone.model.esperienza.Esperienza;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import it.unicam.cs.ids.cicerone.model.utility.PostEsperienza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
@Repository
public interface RepositoryPostEsperienza extends JpaRepository<PostEsperienza, Long> {

    @Query(value = "SELECT * FROM post_esperienza WHERE id_turista = ?1 AND id_esperienza = ?2", nativeQuery = true)
    public PostEsperienza findByTuristaEsperienza(Turista turista, Esperienza esperienza);
}

