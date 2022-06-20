package it.unicam.cs.ids.cicerone.controller.users;


import it.unicam.cs.ids.cicerone.model.esperienza.Esperienza;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import it.unicam.cs.ids.cicerone.model.utility.Prenotazione;
import it.unicam.cs.ids.cicerone.model.utility.StatoPagamento;
import it.unicam.cs.ids.cicerone.repository.esperienza.RepositoryEsperienza;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryTurista;
import it.unicam.cs.ids.cicerone.repository.utility.RepositoryPrenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/turisti")
public class ControllerTurista {
    @Autowired
    private RepositoryTurista repositoryTurista;

    @PostMapping("/add")
    public void addTurista(@RequestBody Turista turista) {
        repositoryTurista.save(turista);
    }

    @GetMapping("/all")
    public List<Turista> getAllTursita() {
        return repositoryTurista.findAll();
    }

    @PutMapping("/update/{idTurista}")
    public ResponseEntity<Turista> updateTurista(@PathVariable("idTurista") Long idTurista, @RequestBody Turista turista) {
        Turista turistaToUpdate = repositoryTurista.findById(idTurista).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turista non trovato"));
        turistaToUpdate.setNome(turista.getNome());
        turistaToUpdate.setCognome(turista.getCognome());
        turistaToUpdate.setResidenza(turista.getResidenza());
        turistaToUpdate.setNumeroTelefonico(turista.getNumeroTelefonico());
        turistaToUpdate.setEmail(turista.getEmail());
        turistaToUpdate.setPassword(new BCryptPasswordEncoder().encode(turista.getPassword()));
        return ResponseEntity.ok(repositoryTurista.save(turistaToUpdate));
    }

    @DeleteMapping("/delete/{idTurista}")
    public void deleteTursita(@PathVariable("idTurista") Long idTurista) {
        Turista turistaToDelete = repositoryTurista.findById(idTurista).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turista non trovato"));
        repositoryTurista.delete(turistaToDelete);
    }
    @GetMapping("/getByUsername")
    public Turista getTuristaByUsername(@RequestParam String username) {
        return repositoryTurista.findByUsername(username);
    }

}
