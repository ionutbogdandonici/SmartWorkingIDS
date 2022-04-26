package it.unicam.cs.ids.cicerone.model.territoriale;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "territori")
@Data
public class Territorio {

    @Id
    @Column(name ="id_territorio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdTerritorio;
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @ManyToOne(targetEntity = Regione.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_regione", nullable = false)
    private Regione regione;
}
