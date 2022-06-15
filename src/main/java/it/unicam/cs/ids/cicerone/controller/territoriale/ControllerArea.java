package it.unicam.cs.ids.cicerone.controller.territoriale;

import it.unicam.cs.ids.cicerone.model.territoriale.Area;
import it.unicam.cs.ids.cicerone.model.territoriale.Territorio;
import it.unicam.cs.ids.cicerone.repository.territoriale.RepositoryArea;
import it.unicam.cs.ids.cicerone.repository.territoriale.RepositoryTerritorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/aree")
public class ControllerArea {

    @Autowired
    private RepositoryArea repositoryArea;

    @Autowired
    private RepositoryTerritorio repositoryTerritorio;

    @PostMapping("/add")
    public void addArea(@RequestBody Area area) {
        if (repositoryArea.findByToponimo(area.getToponimo()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Area già presente");
        } else {
            repositoryArea.save(area);
        }
    }

    @GetMapping("/all")
    public List<Area> getAll() {
        return repositoryArea.findAll();
    }

    @GetMapping("/getByIdTerritorio/{idTerritorio}")
    public List<Area> getByIdTerritorio(@PathVariable("idTerritorio") Long idTerritorio) {
        if(repositoryTerritorio.findById(idTerritorio).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Territorio non presente");
        }
        Territorio territorio = repositoryTerritorio.findById(idTerritorio).orElse(null);
        return repositoryArea.findByTerritorio(territorio);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Area> updateArea(@PathVariable("id") Long id, @RequestBody Area area) {
        Area areaToUpdate = repositoryArea.findById(id).orElse(null);

        if(areaToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Territorio non presente");
        }
        areaToUpdate.setToponimo(area.getToponimo());
        areaToUpdate.setTerritorio(area.getTerritorio());

        return ResponseEntity.ok(repositoryArea.save(areaToUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArea(@PathVariable("id") Long id) {
        if(repositoryArea.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Territorio non presente");
        }
        repositoryArea.deleteById(id);
    }

    @GetMapping("/findById/{idArea}")
    public Area findById(@PathVariable("idArea") Long idArea) {
        if(repositoryArea.findById(idArea).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Area non presente");
        }
        return repositoryArea.findById(idArea).orElse(null);
    }
}
