package it.unicam.cs.ids.cicerone.controller.users;

import it.unicam.cs.ids.cicerone.model.users.Associazione;
import it.unicam.cs.ids.cicerone.model.users.Cicerino;
import it.unicam.cs.ids.cicerone.model.users.Gruppo;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import it.unicam.cs.ids.cicerone.model.utility.Prenotazione;
import it.unicam.cs.ids.cicerone.model.utility.Richiesta;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryAssociazione;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryCicerino;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryGruppo;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryTurista;
import it.unicam.cs.ids.cicerone.repository.utility.RepositoryRichiesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/associazioni")
public class ControllerAssociazione {
    @Autowired
    private RepositoryAssociazione repositoryAssociazione;

    @Autowired
    private RepositoryCicerino repositoryCicerino;

    @Autowired
    private RepositoryRichiesta repositoryRichiesta;

    @Autowired
    private RepositoryGruppo repositoryGruppo;


    @PostMapping("/add")
    public void addAssociazione(@RequestBody Associazione associazione) {
        repositoryAssociazione.save(associazione);
    }

    @GetMapping("/all")
    public List<Associazione> getAllAssociazioni() {
        return repositoryAssociazione.findAll();
    }

    @PutMapping("/update/{idAssociazione}")
    public ResponseEntity<Associazione> updateAssociazione(@PathVariable("idAssociazione") Long idAssociazione, @RequestBody Associazione associazione) {
        Associazione associazioneToUpdate = repositoryAssociazione.findById(idAssociazione).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Associazione non trovato"));
        associazioneToUpdate.setNomeAssociazione(associazione.getNomeAssociazione());
        associazioneToUpdate.setPIva(associazione.getPIva());
        associazioneToUpdate.setResidenza(associazione.getResidenza());
        associazioneToUpdate.setUsername(associazione.getUsername());
        associazioneToUpdate.setEmail(associazione.getEmail());
        associazioneToUpdate.setPassword(new BCryptPasswordEncoder().encode(associazione.getPassword()));
        associazioneToUpdate.setVerificato(associazione.getVerificato());
        return ResponseEntity.ok(repositoryAssociazione.save(associazioneToUpdate));
    }

    @PutMapping("/richiestaAssociazione/{idAssociazione}")
    public ResponseEntity<Richiesta> richiestaAssociazione(@PathVariable("idAssociazione") Long idAssociazione, @RequestBody Cicerino cicerino){
        Cicerino cicerinoToInsert = repositoryCicerino.findById(cicerino.getIdCicerino()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cicerino non trovato"));
        Associazione associazioneToInsert = repositoryAssociazione.findById(idAssociazione).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Associazione non trovata"));
        Richiesta richiesta = new Richiesta();
        richiesta.setAssociazione(associazioneToInsert);
        richiesta.setCicerino(cicerinoToInsert);
        richiesta.setAccepted(false);
        return ResponseEntity.ok(repositoryRichiesta.save(richiesta));
    }

    @PutMapping("/approvaRichiesta/{idAssociazione}")
    public ResponseEntity<Richiesta> approvaRichiesta(@PathVariable("idAssociazione") Long idAssociazione, @RequestBody Richiesta richiesta){
       Richiesta richiestaToApproved = repositoryRichiesta.findById(richiesta.getIdRichiesta()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Richiesta non trovata"));
       Associazione associazione = richiestaToApproved.getAssociazione();
       Cicerino cicerino = richiestaToApproved.getCicerino();
       Gruppo gruppo = repositoryGruppo.finByAssociazione(associazione.getIdAssociazione());
       cicerino.setGruppo(gruppo);
       richiestaToApproved.setAccepted(true);
       return ResponseEntity.ok(repositoryRichiesta.save(richiestaToApproved));
    }

    @DeleteMapping("/delete/{idAssociazione}")
    public void deleteAssociazione(@PathVariable("idAssociazione") Long idAssociazione) {
        Associazione associazioneToDelete = repositoryAssociazione.findById(idAssociazione).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Associazione non trovato"));
        repositoryAssociazione.delete(associazioneToDelete);
    }
}
