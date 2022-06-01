package esperienza;

import it.unicam.cs.ids.cicerone.model.esperienza.Esperienza;
import it.unicam.cs.ids.cicerone.model.territoriale.Area;
import it.unicam.cs.ids.cicerone.repository.esperienza.RepositoryEsperienza;
import it.unicam.cs.ids.cicerone.repository.territoriale.RepositoryArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/esperienze")
public class ControllerEsperienza {

    @Autowired
    private RepositoryEsperienza repositoryEsperienza;
    @Autowired
    private RepositoryArea repositoryArea;

    @PostMapping("/add")
    public void addEsperienza(@RequestBody Esperienza esperienza) {
        repositoryEsperienza.save(esperienza);
    }

    @GetMapping("/all")
    public List<Esperienza> getAllEsperienze() {
        return repositoryEsperienza.findAll();
    }

    @GetMapping("/getByIdArea/{idArea}")
    public List<Esperienza> getEsperienzeByArea(@PathVariable("idArea") Long idArea) {
        Area area = repositoryArea.findById(idArea).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Area non trovata"));
        return repositoryEsperienza.findByArea(area);
    }

    @PutMapping("/update/{idEsperienza}")
    public void updateEsperienza(@PathVariable("idEsperienza") Long idEsperienza, @RequestBody Esperienza esperienza) {
        Esperienza esperienzaToUpdate = repositoryEsperienza.findById(idEsperienza).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esperienza non trovata"));
        esperienzaToUpdate.setNome(esperienza.getNome());
        esperienzaToUpdate.setDescrizione(esperienza.getDescrizione());
        esperienzaToUpdate.setArea(esperienza.getArea());
        esperienzaToUpdate.setCosto(esperienza.getCosto());
        esperienzaToUpdate.setData(esperienza.getData());
        esperienzaToUpdate.setGiorniRiservatezzaPosti(esperienza.getGiorniRiservatezzaPosti());
        esperienzaToUpdate.setPostiInSospeso(esperienza.getPostiInSospeso());
        esperienzaToUpdate.setPostiMax(esperienza.getPostiMax());
        esperienzaToUpdate.setPostiMin(esperienza.getPostiMin());
        esperienzaToUpdate.setPostiRiservati(esperienza.getPostiRiservati());
        esperienzaToUpdate.setScadenzaPrenotazioni(esperienza.getScadenzaPrenotazioni());
        esperienzaToUpdate.setPercorso(esperienza.getPercorso());
        esperienzaToUpdate.setTags(esperienza.getTags());
        repositoryEsperienza.save(esperienzaToUpdate);
    }

    @DeleteMapping("/delete/{idEsperienza}")
    public void deleteEsperienza(@PathVariable("idEsperienza") Long idEsperienza) {
        Esperienza esperienzaToDelete = repositoryEsperienza.findById(idEsperienza).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esperienza non trovata"));
        repositoryEsperienza.delete(esperienzaToDelete);
    }
}
