package it.unicam.cs.ids.cicerone.repository.utility;

import it.unicam.cs.ids.cicerone.model.utility.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTag extends JpaRepository<Tag, Long> {
}
