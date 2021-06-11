package com.example.cuadrangular.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasificacionDTO {

    private long idEquipo;

    private int cantidadPartidos;

    private int cantidadPartidosGanados;

    private int cantidadPartidosPerdidos;

    private int cantidadPartidosEmpate;

    private int cantidadPuntos;

    private int cantidadGoles;

    private int cantidadGolesContra;
}
