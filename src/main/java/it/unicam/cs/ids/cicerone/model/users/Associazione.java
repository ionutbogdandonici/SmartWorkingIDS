package it.unicam.cs.ids.cicerone.model.users;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "associazioni")
@Data
public class Associazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_associazione", nullable = false)
    private Long IdAssociazione;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "nome_associazione", nullable = false)
    private String nomeAssociazione;
    @Column(name = "p_iva", nullable = false)
    private String pIva;
    @Column(name = "residenza", nullable = false)
    private String residenza;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "verificato", nullable = false)
    private Boolean verificato;
}
