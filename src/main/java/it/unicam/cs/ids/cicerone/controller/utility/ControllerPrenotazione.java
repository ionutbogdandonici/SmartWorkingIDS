package it.unicam.cs.ids.cicerone.controller.utility;

import it.unicam.cs.ids.cicerone.model.esperienza.Esperienza;
import it.unicam.cs.ids.cicerone.model.utility.Prenotazione;
import it.unicam.cs.ids.cicerone.model.utility.StatoPagamento;
import it.unicam.cs.ids.cicerone.repository.esperienza.RepositoryEsperienza;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryTurista;
import it.unicam.cs.ids.cicerone.repository.utility.RepositoryPrenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class ControllerPrenotazione {

    @Autowired
    RepositoryPrenotazione repositoryPrenotazione;
    @Autowired
    RepositoryEsperienza repositoryEsperienza;

    @Autowired
    RepositoryTurista repositoryTurista;

    @PostMapping("/add")
    public void addPrenotazione(@RequestBody Prenotazione prenotazione) {
        repositoryPrenotazione.save(prenotazione);
    }

    @GetMapping("/all")
    public List<Prenotazione> getAllPrenotazioni() {
        return repositoryPrenotazione.findAll();
    }

    @PutMapping("{idPrenotazione}/addPartecipante")
    public Boolean addPartecipante(@PathVariable("idPrenotazione") Long idPrenotazione, @RequestBody Long idTurista) {
        Prenotazione prenotazione = repositoryPrenotazione.findById(idPrenotazione).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prenotazione non trovata"));
        prenotazione.setTurista(repositoryTurista.findById(idTurista).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turista non trovato")));
        repositoryPrenotazione.save(prenotazione);
        return true;
    }

    @PutMapping("/update/{idPrenotazione}")
    public void updateStatoPrenotazione(@PathVariable("idPrenotazione") Long idPrenotazione, @RequestBody StatoPagamento statoPagamento) {
        Prenotazione prenotazioneToUpdate = repositoryPrenotazione.findById(idPrenotazione).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prenotazione non trovata"));
        prenotazioneToUpdate.setStato_pagamento(prenotazioneToUpdate.getStato_pagamento());
        repositoryPrenotazione.save(prenotazioneToUpdate);
    }

    @GetMapping("/checkPostiDisponibili/{idPrenotazione}")
    public Boolean checkPostiDisponibili(@PathVariable("idPrenotazione") Long idPrenotazione) {
        Prenotazione prenotazione = repositoryPrenotazione.findById(idPrenotazione).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prenotazione non trovata"));
        Esperienza esperienza = repositoryEsperienza.findById(prenotazione.getEsperienza().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esperienza non trovata"));
        return (esperienza.getPostiMax() - esperienza.getPostiRiservati() >= prenotazione.getNumeroPosti());
    }

    @GetMapping("getPrenotazioneById/{idPrenotazione}")
    public Prenotazione getPrenotazioneById(@PathVariable("idPrenotazione") Long idPrenotazione) {
        return repositoryPrenotazione.findById(idPrenotazione).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prenotazione non trovata"));
    }
}
