package it.unicam.cs.ids.cicerone.controller.utility;

import it.unicam.cs.ids.cicerone.model.users.Cicerino;
import it.unicam.cs.ids.cicerone.model.utility.Richiesta;
import it.unicam.cs.ids.cicerone.repository.utility.RepositoryRichiesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/richieste")
public class ControllerRichiesta {
    @Autowired
    RepositoryRichiesta repositoryRichiesta;

    @GetMapping("/all")
    public List<Richiesta> getAll(){ return repositoryRichiesta.findAll();}

    @GetMapping("/getRichiestaByAssociazione/{idAssociazione}")
    public ResponseEntity<Richiesta> getRichiestaByAssociazione(@PathVariable Long idAssociazione){
        Richiesta richiesta=  repositoryRichiesta.findByAssociazione(idAssociazione);
        return ResponseEntity.ok(richiesta);
    }


}
