package it.unicam.cs.ids.cicerone.controller.utility;

import it.unicam.cs.ids.cicerone.model.esperienza.Esperienza;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import it.unicam.cs.ids.cicerone.model.utility.PostEsperienza;
import it.unicam.cs.ids.cicerone.repository.utility.RepositoryPostEsperienza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/postEsperienza")
public class ControllerPostEsperienza {

    @Autowired
    private RepositoryPostEsperienza repositoryPostEsperienza;

    @PostMapping("/add")
    public void addPostEsperienza(@RequestBody PostEsperienza postEsperienza) {
        repositoryPostEsperienza.save(postEsperienza);
    }

    @GetMapping("/findPostByTuristaEsperienza")
    public PostEsperienza findPostByTuristaEsperienza(@RequestBody Turista turista, @RequestBody Esperienza esperienza) {
        return repositoryPostEsperienza.findByTuristaEsperienza(turista, esperienza);
    }
}
