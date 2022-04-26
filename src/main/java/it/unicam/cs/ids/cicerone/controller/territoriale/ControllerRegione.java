package it.unicam.cs.ids.cicerone.controller.territoriale;

import it.unicam.cs.ids.cicerone.model.territoriale.Regione;
import it.unicam.cs.ids.cicerone.repository.territoriale.RepositoryRegione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regioni")
public class ControllerRegione {

    @Autowired
    private RepositoryRegione repositoryRegione;

    @PostMapping("/add")
    public void addRegione(@RequestBody Regione regione){
        repositoryRegione.save(regione);
    }

    @GetMapping("/all")
    public List<Regione> getAllRegione(){
        return repositoryRegione.findAll();
    }

    @GetMapping("/{id}")
    public Regione getRegione(@PathVariable("id") Long id){
        return repositoryRegione.findById(id).get();
    }

}
