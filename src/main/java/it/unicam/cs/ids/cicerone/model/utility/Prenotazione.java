package it.unicam.cs.ids.cicerone.model.utility;

import it.unicam.cs.ids.cicerone.model.esperienza.Esperienza;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import it.unicam.cs.ids.cicerone.model.utility.StatoPagamento;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "prenotazioni")
@Data
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenotazione", nullable = false)
    private Long IdPrenotazione;
    @ManyToOne
    @JoinColumn(name = "id_esperienza", nullable = false)
    private Esperienza esperienza;
    @ManyToOne
    @JoinColumn(name = "id_turista", nullable = false)
    private Turista turista;
    @Column(name = "data_prenotazione", nullable = false)
    private Date dataPrenotazione;
    @Column(name= "numero_posti", nullable = false)
    private int numeroPosti;
    @Column(name="stato_pagamento", nullable=false)
    private String stato_pagamento;


}
