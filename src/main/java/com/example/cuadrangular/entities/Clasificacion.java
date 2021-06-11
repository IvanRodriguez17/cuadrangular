package com.example.cuadrangular.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Clasificacion {

    @Id @GeneratedValue
    private long id;

    private long idEquipo;

    private int cantidadPartidos;

    private int cantidadPartidosGanados;

    private int cantidadPartidosPerdidos;

    private int cantidadPartidosEmpate;

    private int cantidadPuntos;

    private int cantidadGoles;

    private int cantidadGolesContra;

}
