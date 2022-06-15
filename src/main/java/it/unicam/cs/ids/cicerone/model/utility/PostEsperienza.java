package it.unicam.cs.ids.cicerone.model.utility;

import it.unicam.cs.ids.cicerone.model.esperienza.Esperienza;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "post_esperienza")
@Data
public class PostEsperienza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post_esperienza", nullable = false)
    private Long idPostEsperienza;
    @ManyToOne
    @JoinColumn(name = "id_esperienza", nullable = false)
    private Esperienza esperienza;
    @ManyToOne
    @JoinColumn(name = "id_turista", nullable = false)
    private Turista turista;
    @Column(name = "data_pubblicazione", nullable = false)
    private Date dataPubblicazione;
}
