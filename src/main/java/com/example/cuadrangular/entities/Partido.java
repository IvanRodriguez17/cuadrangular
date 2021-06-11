package com.example.cuadrangular.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PARTIDOS")
public class Partido {

    @Id @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "equipo_local")
    private long idEquipoLocal;

    @Column(name = "equipo_visitante")
    private long idEquipoVisitante;

    @Column(name = "goles_local")
    private int golesLocal;

    @Column(name = "goles_visitante")
    private int golesVisitante;
}
