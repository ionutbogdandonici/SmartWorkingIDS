package it.unicam.cs.ids.cicerone.model.esperienza;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.unicam.cs.ids.cicerone.model.territoriale.Area;
import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "percorsi")
@Data
public class Percorso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_percorso", nullable = false)
    private Long IdPercorso;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "id_area", nullable = false)
    private Area area;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "percorsi_tappe", joinColumns = @JoinColumn(name = "id_tappa"), inverseJoinColumns = @JoinColumn(name = "id_percorso"))
    private List<Tappa> tappe = new LinkedList<>();

    /**
     * Aggiunge una tappa al percorso
     *
     * @param tappa tappa da aggiungere al percorso
     */
    public void addTappa(Tappa tappa) {
        this.tappe.add(tappa);
    }

    /**
     * Rimuove una tappa dal percorso
     *
     * @param tappa tappa da rimuovere dal percorso
     */
    public void removeTappa(Tappa tappa) {
        this.tappe.remove(tappa);
    }
}
