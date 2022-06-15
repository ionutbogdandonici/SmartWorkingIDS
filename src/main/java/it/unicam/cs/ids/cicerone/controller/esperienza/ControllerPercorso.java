package it.unicam.cs.ids.cicerone.controller.esperienza;

import it.unicam.cs.ids.cicerone.model.esperienza.Percorso;
import it.unicam.cs.ids.cicerone.model.esperienza.Tappa;
import it.unicam.cs.ids.cicerone.model.territoriale.Area;
import it.unicam.cs.ids.cicerone.repository.esperienza.RepositoryPercorso;
import it.unicam.cs.ids.cicerone.repository.esperienza.RepositoryTappa;
import it.unicam.cs.ids.cicerone.repository.territoriale.RepositoryArea;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/percorsi")
@Slf4j
public class ControllerPercorso {

    @Autowired
    private RepositoryPercorso repositoryPercorso;
    @Autowired
    private RepositoryTappa repositoryTappa;
    @Autowired
    private RepositoryArea repositoryArea;

    @PostMapping("/add")
    public void addPercorso(@RequestBody Percorso percorso) {
        if (repositoryPercorso.findByNomeAndArea(percorso.getNome(), percorso.getArea()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Percorso già presente");
        }
        //repositoryPercorso.findById(percorso.getIdPercorso()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Percorso non trovato"));
        repositoryPercorso.save(percorso);
    }

    @GetMapping("/all")
    public List<Percorso> getAllPercorsi() {
        return repositoryPercorso.findAll();
    }

    @GetMapping("/getByArea/{idArea}")
    public List<Percorso> getPercorsiByArea(@PathVariable("idArea") Long idArea) {
        Area area = repositoryArea.findById(idArea).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Area non trovata"));
        return repositoryPercorso.findByArea(area);
    }

    @PutMapping("/addTappa/{idPercorso}")
    public void addTappaToPercorso(@PathVariable("idPercorso") Long idPercorso, @RequestBody Tappa tappa) {
        Percorso percorso = repositoryPercorso.findById(idPercorso).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Percorso non trovato"));
        Tappa _tappa = repositoryTappa.findById(tappa.getIdTappa()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tappa non trovata"));
        percorso.addTappa(_tappa);
        repositoryPercorso.save(percorso);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Percorso> updatePercorso(@PathVariable("id") Long id, @RequestBody Percorso percorso) {
        Percorso _percorso = repositoryPercorso.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Percorso non trovato"));
        _percorso.setDescrizione(percorso.getDescrizione());
        _percorso.setArea(percorso.getArea());
        _percorso.setNome(percorso.getNome());
        _percorso.setTappe(percorso.getTappe());
        return ResponseEntity.ok(repositoryPercorso.save(_percorso));
    }

    @PutMapping("/removeTappa/{idPercorso}")
    public void removeTappaFromPercorso(@PathVariable("idPercorso") Long idPercorso, @RequestBody Tappa tappa) {
        Percorso percorso = repositoryPercorso.findById(idPercorso).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Percorso non trovato"));
        Tappa _tappa = repositoryTappa.findById(tappa.getIdTappa()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tappa non trovata"));
        percorso.removeTappa(_tappa);
        repositoryPercorso.save(percorso);
    }

    @DeleteMapping("/delete/{idPercorso}")
    public void deletePercorso(@PathVariable("idPercorso") Long idPercorso) {
        repositoryPercorso.deleteById(idPercorso);
    }

    @GetMapping("/findById/{idPercorso}")
    public Percorso findById(@PathVariable("idPercorso") Long idPercorso) {
        return repositoryPercorso.findById(idPercorso).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Percorso non trovato"));
    }
}
