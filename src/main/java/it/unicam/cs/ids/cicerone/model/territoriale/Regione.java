package it.unicam.cs.ids.cicerone.model.territoriale;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regioni")

@Data
public class Regione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_regione", nullable = false)
    private Long IdRegione;
    @Column(name = "nome", nullable = false)
    private String nome;
}

