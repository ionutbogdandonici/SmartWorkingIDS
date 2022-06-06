package it.unicam.cs.ids.cicerone.model.utility;

import it.unicam.cs.ids.cicerone.model.esperienza.Esperienza;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import it.unicam.cs.ids.cicerone.model.utility.StatoPagamento;
import lombok.Data;

import javax.persistence.*;

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
    @Enumerated(EnumType.STRING)
    private StatoPagamento stato_pagamento;



}
