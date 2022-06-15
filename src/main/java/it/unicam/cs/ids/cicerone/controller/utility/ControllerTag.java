package it.unicam.cs.ids.cicerone.controller.utility;

import it.unicam.cs.ids.cicerone.model.utility.Tag;
import it.unicam.cs.ids.cicerone.repository.utility.RepositoryTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTag {

    @Autowired
    private RepositoryTag repositoryTag;

    @PostMapping("/add")
    public void addTag(@RequestBody Tag tag) {
        repositoryTag.save(tag);
    }
}
