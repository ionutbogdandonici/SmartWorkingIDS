package it.unicam.cs.ids.cicerone.controller.utility;

import it.unicam.cs.ids.cicerone.model.utility.Tag;
import it.unicam.cs.ids.cicerone.repository.utility.RepositoryTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ControllerTag {

    @Autowired
    private RepositoryTag repositoryTag;

    @PostMapping("/add")
    public void addTag(@RequestBody Tag tag) {
        repositoryTag.save(tag);
    }

    @GetMapping("/getAll")
    public List<Tag> getAllTags() {
        return repositoryTag.findAll();
    }

    @GetMapping("/findById/{idTag}")
    public Tag findById(@PathVariable("idTag") Long idTag) {
        return repositoryTag.findById(idTag).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag non trovato"));
    }

    @GetMapping("/getAllWhereNotAbled")
    public List<Tag> getAllWhereNotAbled() {
        return repositoryTag.findAll().stream().filter(tag -> !tag.isAbled()).collect(java.util.stream.Collectors.toList());
    }

    @PutMapping("able/{idTag}")
    public void acceptTag(@PathVariable("idTag") Long idTag, @RequestBody Boolean abled) {
        Tag tag = repositoryTag.findById(idTag).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag non trovato"));
        if (abled) {
            tag.setAbled(true);
            repositoryTag.save(tag);
        } else {
            repositoryTag.delete(tag);
        }
    }

    @PutMapping("/update/{idTag}")
    public void updateTag(@PathVariable("idTag") Long idTag, @RequestBody Tag tag) {
        Tag tagToUpdate = repositoryTag.findById(idTag).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag non trovato"));
        tagToUpdate.setNome(tag.getNome());
    }

    @DeleteMapping("/delete/{idTag}")
    public void deleteTag(@PathVariable("idTag") Long idTag) {
        Tag tagToDelete = repositoryTag.findById(idTag).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag non trovato"));
        repositoryTag.delete(tagToDelete);
    }
}
