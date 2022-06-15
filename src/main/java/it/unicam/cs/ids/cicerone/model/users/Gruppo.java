package it.unicam.cs.ids.cicerone.model.users;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "gruppi")
@Data
public class Gruppo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gruppo", nullable = false)
    private Long IdGruppo;
    @Column(name = "nome", nullable = false)
    private String nomeGruppo;
    @ManyToOne
    @JoinColumn(name = "id_associazione", nullable = false)
    private Associazione associazione;
}
