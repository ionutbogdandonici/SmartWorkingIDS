package it.unicam.cs.ids.cicerone.model.utility;

import it.unicam.cs.ids.cicerone.model.esperienza.Esperienza;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tags")
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name="tag_esperienza",
            joinColumns=@JoinColumn(name="id_tag"),
            inverseJoinColumns=@JoinColumn(name="id_esperienza"))
    private java.util.List<Esperienza> esperienze;
}