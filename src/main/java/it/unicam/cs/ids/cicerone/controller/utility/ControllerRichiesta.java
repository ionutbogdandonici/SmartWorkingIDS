package it.unicam.cs.ids.cicerone.controller.utility;

import it.unicam.cs.ids.cicerone.model.users.Associazione;
import it.unicam.cs.ids.cicerone.model.users.Cicerino;
import it.unicam.cs.ids.cicerone.model.users.Gruppo;
import it.unicam.cs.ids.cicerone.model.utility.Richiesta;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryAssociazione;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryCicerino;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryGruppo;
import it.unicam.cs.ids.cicerone.repository.utility.RepositoryRichiesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/richieste")
public class ControllerRichiesta {
    @Autowired
    RepositoryRichiesta repositoryRichiesta;

    @Autowired
    RepositoryGruppo repositoryGruppo;
    @Autowired
    RepositoryCicerino repositoryCicerino;
    @Autowired
    RepositoryAssociazione repositoryAssociazione;

    @GetMapping("/all")
    public List<Richiesta> getAll() {
        return repositoryRichiesta.findAll();
    }

    @PutMapping("/accept/{idRichiesta}")
    public void accept(@PathVariable("idRichiesta") Long idRichiesta, @RequestBody Boolean accepted) {
        Richiesta richiestaToUpdate = repositoryRichiesta.findById(idRichiesta).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Richiesta non trovata"));
        Cicerino cicerino = repositoryCicerino.getById(richiestaToUpdate.getCicerino().getIdCicerino());
        Associazione associazione = repositoryAssociazione.getById(richiestaToUpdate.getAssociazione().getIdAssociazione());
        if (accepted) {
            Gruppo gruppo = repositoryGruppo.finByAssociazione(associazione.getIdAssociazione());
            cicerino.setGruppo(gruppo);
            repositoryCicerino.save(cicerino);
        }
        repositoryRichiesta.delete(richiestaToUpdate);
    }

}
