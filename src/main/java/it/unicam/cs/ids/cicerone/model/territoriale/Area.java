package it.unicam.cs.ids.cicerone.model.territoriale;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "aree")
@Data
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area", nullable = false)
    private Long IdArea;
    @Column(name = "toponimo", nullable = false)
    private String toponimo;
    @ManyToOne
    @JoinColumn(name = "id_territorio", nullable = false)
    private Territorio territorio;
}