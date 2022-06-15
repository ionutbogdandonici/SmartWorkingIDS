package it.unicam.cs.ids.cicerone.controller.users;

import it.unicam.cs.ids.cicerone.model.users.Associazione;
import it.unicam.cs.ids.cicerone.model.users.Cicerino;
import it.unicam.cs.ids.cicerone.model.users.Gruppo;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import it.unicam.cs.ids.cicerone.model.utility.GiorniSettimana;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryAssociazione;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryCicerino;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryGruppo;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryTurista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cicerini")
public class ControllerCicerino {

    @Autowired
    private RepositoryCicerino repositoryCicerino;

    @Autowired
    private RepositoryAssociazione repositoryAssociazione;

    @Autowired
    private RepositoryGruppo repositoryGruppo;

    @PostMapping("/add")
    public void addCicerino(@RequestBody Cicerino cicerino) {
        repositoryCicerino.save(cicerino);
    }

    @GetMapping("/all")
    public List<Cicerino> getAllCicerino() {
        return repositoryCicerino.findAll();
    }

    @PutMapping("/partecipaAssociazione/{idCicerino}")
    public ResponseEntity<Cicerino> partecipaAssociazione(@PathVariable("idCicerino") Long idCicerino, @RequestBody Associazione associazione){
        Cicerino cicerino = repositoryCicerino.findById(idCicerino).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cicerino non trovato"));
        Associazione associazioneToInsert = repositoryAssociazione.findById(associazione.getIdAssociazione()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Associazione non trovata"));
        Gruppo gruppoToInsert = repositoryGruppo.finByAssociazione(associazione.getIdAssociazione());
        cicerino.setGruppo(gruppoToInsert);
        return ResponseEntity.ok(repositoryCicerino.save(cicerino));
    }

    @PutMapping("/update/{idCicerino}")
    public ResponseEntity<Cicerino> updateCicerino(@PathVariable("idCicerino") Long idCicerino, @RequestBody Cicerino cicerino) {
        Cicerino cicerinoToUpdate = repositoryCicerino.findById(idCicerino).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cicerino non trovato"));
        cicerinoToUpdate.setNome(cicerino.getNome());
        cicerinoToUpdate.setCognome(cicerino.getCognome());
        cicerinoToUpdate.setResidenza(cicerino.getResidenza());
        cicerinoToUpdate.setEmail(cicerino.getEmail());
        cicerinoToUpdate.setDataNascita(cicerino.getDataNascita());
        cicerinoToUpdate.setUsername(cicerino.getUsername());
        cicerinoToUpdate.setPassword(new BCryptPasswordEncoder().encode(cicerino.getPassword()));
        cicerinoToUpdate.setVerificato(cicerino.getVerificato());
        return ResponseEntity.ok(repositoryCicerino.save(cicerinoToUpdate));
    }

    @DeleteMapping("/delete/{idCicerino}")
    public void deleteCicerino(@PathVariable("idCicerino") Long idCicerino) {
        Cicerino cicerinoToDelete = repositoryCicerino.findById(idCicerino).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cicerino non trovato"));
        repositoryCicerino.delete(cicerinoToDelete);
    }

    @GetMapping("/getAllCiceriniNotAbled")
    public List<Cicerino> getAllCiceriniNotAbled() {
        return repositoryCicerino.findAll().stream().filter(cicerino -> !cicerino.getVerificato()).collect(Collectors.toList());
    }

    @GetMapping("{idCicerino}")
    public Cicerino getCicerinoById(@PathVariable("idCicerino") Long idCicerino) {
        return repositoryCicerino.findById(idCicerino).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cicerino non trovato"));
    }

    @PutMapping("/able/{idCicerino}")
    public ResponseEntity<Cicerino> ableCicerino(@PathVariable("idCicerino") Long idCicerino) {
        Cicerino cicerinoToUpdate = repositoryCicerino.findById(idCicerino).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cicerino non trovato"));
        cicerinoToUpdate.setVerificato(true);
        return ResponseEntity.ok(repositoryCicerino.save(cicerinoToUpdate));
    }

    @PostMapping("impostaDisponibilita/{idCicerino}")
    public ResponseEntity<Cicerino> impostaDisponibilita(@PathVariable("idCicerino") Long idCicerino, @RequestBody List<GiorniSettimana> giorniSettimana) {
        Cicerino cicerinoToUpdate = repositoryCicerino.findById(idCicerino).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cicerino non trovato"));
        cicerinoToUpdate.setGiorniSettimana(giorniSettimana);
        return ResponseEntity.ok(repositoryCicerino.save(cicerinoToUpdate));
    }

}
