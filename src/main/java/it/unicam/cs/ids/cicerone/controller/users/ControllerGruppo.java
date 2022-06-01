package it.unicam.cs.ids.cicerone.controller.users;

import it.unicam.cs.ids.cicerone.model.users.Associazione;
import it.unicam.cs.ids.cicerone.model.users.Gruppo;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryAssociazione;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryGruppo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/gruppi")
public class ControllerGruppo {
    @Autowired
    private RepositoryGruppo repositoryGruppo;

    @PostMapping("/add")
    public void addGruppo(@RequestBody Gruppo gruppo) {
        repositoryGruppo.save(gruppo);
    }

    @GetMapping("/all")
    public List<Gruppo> getAllGruppi() {
        return repositoryGruppo.findAll();
    }

    @PutMapping("/update/{idGruppo}")
    public ResponseEntity<Gruppo> updateGruppo(@PathVariable("idGruppo") Long idGruppo, @RequestBody Gruppo gruppo) {
        Gruppo gruppoToUpdate = repositoryGruppo.findById(idGruppo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gruppo non trovato"));
        gruppoToUpdate.setNomeGruppo(gruppo.getNomeGruppo());
        gruppoToUpdate.setAssociazione(gruppo.getAssociazione());
        return ResponseEntity.ok(repositoryGruppo.save(gruppoToUpdate));
    }

    @GetMapping("/getByAssociazione/{idAssociazione}")
    public ResponseEntity<Gruppo> getGruppoByIdAssociazione (@PathVariable("idAssociazione") Long idAssociazione){
        return ResponseEntity.ok(repositoryGruppo.finByAssociazione(idAssociazione));
    }

    @DeleteMapping("/delete/{idGruppo}")
    public void deleteGruppo(@PathVariable("idGruppo") Long idGruppo) {
        Gruppo gruppoToDelete = repositoryGruppo.findById(idGruppo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gruppo non trovato"));
        repositoryGruppo.delete(gruppoToDelete);
    }
}
