package com.example.cuadrangular.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartidoDTO {

    private long idEquipoLocal;

    private long idEquipoVisitante;

    private int golesLocal;

    private int golesVisitante;
}
