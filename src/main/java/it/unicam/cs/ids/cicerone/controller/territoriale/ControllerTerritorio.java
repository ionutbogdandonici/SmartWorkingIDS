package it.unicam.cs.ids.cicerone.controller.territoriale;

import it.unicam.cs.ids.cicerone.model.territoriale.Regione;
import it.unicam.cs.ids.cicerone.model.territoriale.Territorio;
import it.unicam.cs.ids.cicerone.repository.territoriale.RepositoryRegione;
import it.unicam.cs.ids.cicerone.repository.territoriale.RepositoryTerritorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/territori")
public class ControllerTerritorio {

    @Autowired
    private RepositoryTerritorio repositoryTerritorio;
    @Autowired
    private RepositoryRegione repositoryRegione;

    @PostMapping("/add")
    public void addTerritorio(@RequestBody Territorio territorio) {
        if (repositoryTerritorio.findByNome(territorio.getNome()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Territorio già presente");
        }
        repositoryTerritorio.save(territorio);
    }


    @GetMapping("/all")
    public List<Territorio> getAllTerritori() {
        return repositoryTerritorio.findAll();
    }


    @GetMapping("/findById/{id}")
    public Territorio findById(@PathVariable Long id) {
        if (repositoryTerritorio.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Territorio non presente");
        }
        return repositoryTerritorio.findById(id).orElse(null);
    }

    @GetMapping("/getByIdRegione/{idRegione}")
    public List<Territorio> getTerritoriByRegione(@PathVariable("idRegione") Long idRegione) {
        if (repositoryRegione.findById(idRegione).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Regione non presente");
        }
        Regione regione = repositoryRegione.findById(idRegione).orElse(null);
        return repositoryTerritorio.findByRegione(regione);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Territorio> updateTerritorio(@PathVariable("id") Long id, @RequestBody Territorio territorio) {
        Territorio territorioToUpdate = repositoryTerritorio.findById(id).orElse(null);

        if (territorioToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Territorio non presente");
        }
        territorioToUpdate.setNome(territorio.getNome());
        territorioToUpdate.setRegione(territorio.getRegione());

        return ResponseEntity.ok(repositoryTerritorio.save(territorioToUpdate));

    }

    @DeleteMapping("/delete/{id}")
    public void deleteTerritorio(@PathVariable Long id) {
        if (repositoryTerritorio.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Territorio non presente");
        }
        repositoryTerritorio.deleteById(id);
    }
}

