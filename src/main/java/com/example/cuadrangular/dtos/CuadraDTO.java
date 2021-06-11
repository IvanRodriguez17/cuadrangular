package com.example.cuadrangular.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuadraDTO {
    private List<EquipoDTO> equipos;
}
