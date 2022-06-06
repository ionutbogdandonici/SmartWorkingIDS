package it.unicam.cs.ids.cicerone.model.users;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "turisti")
@Data
public class Turista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turista", nullable = false)
    private Long IdTurista;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cognome", nullable = false)
    private String cognome;
    @Column(name = "numeroTelefonico", nullable = false)
    private String numeroTelefonico;
    @Column(name = "residenza", nullable = false)
    private String residenza;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
}
