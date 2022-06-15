package it.unicam.cs.ids.cicerone.controller.esperienza;

import it.unicam.cs.ids.cicerone.model.esperienza.Tappa;
import it.unicam.cs.ids.cicerone.model.territoriale.Area;
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
@RequestMapping("/tappe")
@Slf4j
public class ControllerTappa {

    @Autowired
    private RepositoryTappa repositoryTappa;
    @Autowired
    private RepositoryArea repositoryArea;

    @PostMapping("/add")
    public void addTappa(@RequestBody Tappa tappa) {
        log.info("Tappa aggiunta: {} ", tappa);

       if (repositoryTappa.findByNomeAndDescrizioneAndRaggiunta(tappa.getNome(), tappa.getDescrizione(), tappa.getRaggiunta()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tappa già presente");
        }

        repositoryTappa.save(tappa);
    }

    @GetMapping("/all")
    public List<Tappa> getAllTappe() {
        return repositoryTappa.findAll();
    }

    @GetMapping("getByArea/{idArea}")
    public List<Tappa> getTappeByArea(@PathVariable("idArea") Long idArea) {
        Area area = repositoryArea.findById(idArea).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Area non trovata"));
        return repositoryTappa.findByAreaTappa(area);
    }

    @PutMapping("/update/{idTappa}")
    public ResponseEntity<Tappa> update(@PathVariable("idTappa") Long idTappa, @RequestBody Tappa tappa) {
        Tappa tappaToUpdate = repositoryTappa.findById(idTappa).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tappa non trovata"));
        tappaToUpdate.setNome(tappa.getNome());
        tappaToUpdate.setDescrizione(tappa.getDescrizione());
        tappaToUpdate.setRaggiunta(tappa.getRaggiunta());
        return ResponseEntity.ok(repositoryTappa.save(tappaToUpdate));
    }

    @DeleteMapping("/delete/{idTappa}")
    public void deleteTappa(@PathVariable("idTappa") Long idTappa) {
        Tappa tappaToDelete = repositoryTappa.findById(idTappa).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tappa non trovata"));
        repositoryTappa.delete(tappaToDelete);
    }

    @GetMapping("/findById/{idTappa}")
    public Tappa findById(@PathVariable("idTappa") Long idTappa) {
        return repositoryTappa.findById(idTappa).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tappa non trovata"));
    }


}
