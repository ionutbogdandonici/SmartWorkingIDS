package it.unicam.cs.ids.cicerone.repository.utility;

import it.unicam.cs.ids.cicerone.model.esperienza.Esperienza;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import it.unicam.cs.ids.cicerone.model.utility.PostEsperienza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;

public interface RepositoryPostEsperienza extends JpaRepository<PostEsperienza, Long> {


    public PostEsperienza findByTuristaEsperienza(Turista turista, Esperienza esperienza);
}

