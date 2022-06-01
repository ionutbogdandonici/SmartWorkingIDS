package it.unicam.cs.ids.cicerone.controller.users;

import it.unicam.cs.ids.cicerone.model.users.Admin;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryAdmin;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryGruppo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ControllerAdmin {
    @Autowired
    private RepositoryAdmin repositoryAdmin;

    @PostMapping("/add")
    public void addTurista(@RequestBody Admin admin) {
        repositoryAdmin.save(admin);
    }

    @GetMapping("/all")
    public List<Admin> getAllAdmin() {
        return repositoryAdmin.findAll();
    }

    @PutMapping("/update/{idAdmin}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("idAdmin") Long idAdmin, @RequestBody Admin admin) {
        Admin adminToUpdate = repositoryAdmin.findById(idAdmin).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin non trovato"));
        adminToUpdate.setEmail(admin.getEmail());
        adminToUpdate.setPassword(admin.getPassword());
        adminToUpdate.setUsername(admin.getUsername());
        return ResponseEntity.ok(repositoryAdmin.save(adminToUpdate));
    }

    @DeleteMapping("/delete/{idTurista}")
    public void deleteAdmin(@PathVariable("idAdmin") Long idAdmin) {
        Admin adminToDelete = repositoryAdmin.findById(idAdmin).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin non trovato"));
        repositoryAdmin.delete(adminToDelete);
    }
}
