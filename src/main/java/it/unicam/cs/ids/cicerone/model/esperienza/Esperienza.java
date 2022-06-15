package it.unicam.cs.ids.cicerone.model.esperienza;

import it.unicam.cs.ids.cicerone.model.territoriale.Area;
import it.unicam.cs.ids.cicerone.model.users.Associazione;
import it.unicam.cs.ids.cicerone.model.users.Cicerino;
import it.unicam.cs.ids.cicerone.model.utility.Tag;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "esperienze")
@Data
public class Esperienza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_esperienza", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "id_area", nullable = false)
    private Area area;

    @Column(name = "data_esperienza", nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "id_percroso", nullable = false)
    private Percorso percorso;

    @Column(name = "costo", nullable = false)
    private float costo;

    @Column(name = "postiMax", nullable = false)
    private int postiMax;

    @Column(name = "postiMin", nullable = false)
    private int postiMin;

    @Column(name = "postiRiservati", nullable = false)
    private int postiRiservati;

    @Column(name = "postiInSospeso", nullable = false)
    private int postiInSospeso;

    @Column(name = "scadenzaPrenotiazioni", nullable = false)
    private Date scadenzaPrenotazioni;

    @Column(name = "giorniRiservatezzaPosti", nullable = false)
    private int giorniRiservatezzaPosti;

    @Column(name = "assegnta", nullable = true)
    private boolean assegnata;

    @ManyToMany
    @JoinTable(name = "tag_esperienza",
            joinColumns = @JoinColumn(name = "id_esperienza"),
            inverseJoinColumns = @JoinColumn(name = "id_tag"))
    private List<Tag> tags;


}
