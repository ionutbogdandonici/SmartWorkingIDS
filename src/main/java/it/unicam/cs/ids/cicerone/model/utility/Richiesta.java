package it.unicam.cs.ids.cicerone.model.utility;

import it.unicam.cs.ids.cicerone.model.users.Associazione;
import it.unicam.cs.ids.cicerone.model.users.Cicerino;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "richieste")
@Data
public class Richiesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_richiesta", nullable = false)
    private Long IdRichiesta;
    @ManyToOne
    @JoinColumn(name = "id_associazione", nullable = false)
    private Associazione associazione;
    @OneToOne
    @JoinColumn(name ="id_cicerino", nullable = false)
    private Cicerino cicerino;
    @Column(name="is_accepted", nullable = false, columnDefinition = "boolean default false")
    private boolean isAccepted;

}
