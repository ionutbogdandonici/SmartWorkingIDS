package it.unicam.cs.ids.cicerone.model.esperienza;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.unicam.cs.ids.cicerone.model.territoriale.Area;
import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "tappe")
@Data
public class Tappa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tappa", nullable = false)
    private Long IdTappa;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "descrizione", nullable = false)
    private String descrizione;
    @Column(name="raggiunta", nullable = false)
    private String raggiunta;
    @ManyToOne
    @JoinColumn(name = "id_area", nullable = false)
    private Area areaTappa;
    @ManyToMany(mappedBy = "tappe", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonBackReference
    private List<Percorso> percorsi = new LinkedList<>();
}