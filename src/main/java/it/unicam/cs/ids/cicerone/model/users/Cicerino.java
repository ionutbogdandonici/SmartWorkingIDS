package it.unicam.cs.ids.cicerone.model.users;

import it.unicam.cs.ids.cicerone.model.territoriale.Territorio;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cicerini")
@Data
public class Cicerino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cicerino", nullable = false)
    private Long IdCicerino;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cognome", nullable = false)
    private String cognome;
    @Column(name = "dataNascita", nullable = false)
    private Date dataNascita;
    @Column(name = "residenza", nullable = false)
    private String residenza;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "verificato", nullable = false)
    private Boolean verificato;
    @ManyToOne
    @JoinColumn(name = "id_gruppo", nullable = true)
    private Gruppo gruppo;

}
