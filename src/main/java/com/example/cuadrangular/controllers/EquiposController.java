package com.example.cuadrangular.controllers;

import com.example.cuadrangular.dtos.CuadraDTO;
import com.example.cuadrangular.dtos.PartidoDTO;
import com.example.cuadrangular.servicies.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EquiposController {

    @Autowired
    public EquipoService equipoService;

    @GetMapping("/equipos")
    public ResponseEntity obtenerEquipos(){
        return new ResponseEntity(equipoService.obtenerEquipos(), HttpStatus.OK);
    }

    @PostMapping("/crearCuadrangular")
    public ResponseEntity crearEquipos(@RequestBody CuadraDTO cuadraDTO){
        return new ResponseEntity(equipoService.crearEquipos(cuadraDTO), HttpStatus.CREATED);
    }

    @PostMapping("/crearPartido")
    public ResponseEntity crearPartido(@RequestBody PartidoDTO partidoDTO){
        return new ResponseEntity(equipoService.definirMarcador(partidoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/partidos")
    public ResponseEntity obtenerPartidos(){
        return new ResponseEntity(equipoService.obtenerPartidos(), HttpStatus.OK);
    }

    @GetMapping("/resultados")
    public ResponseEntity mostrarResultados() {
        return new ResponseEntity(equipoService.mostrarResultados(), HttpStatus.OK);
    }
}
