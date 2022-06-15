package it.unicam.cs.ids.cicerone.model.utility;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GiorniSettimana {

    @Id
    private Long id;

    private String giorno;
}
