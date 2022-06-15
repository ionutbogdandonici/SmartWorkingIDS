package it.unicam.cs.ids.cicerone.repository.users;

import it.unicam.cs.ids.cicerone.model.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAdmin extends JpaRepository<Admin,Long> {
    public Admin findByUsername(String username);
}
