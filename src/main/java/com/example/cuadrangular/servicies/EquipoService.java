package com.example.cuadrangular.servicies;

import com.example.cuadrangular.dtos.ClasificacionDTO;
import com.example.cuadrangular.dtos.CuadraDTO;
import com.example.cuadrangular.dtos.EquipoDTO;
import com.example.cuadrangular.dtos.PartidoDTO;
import com.example.cuadrangular.entities.Clasificacion;
import com.example.cuadrangular.entities.Equipo;
import com.example.cuadrangular.entities.Partido;
import com.example.cuadrangular.repositories.ClasificacionRepository;
import com.example.cuadrangular.repositories.EquipoRepository;
import com.example.cuadrangular.repositories.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private ClasificacionRepository clasificacionRepository;

    public List<EquipoDTO> obtenerEquipos(){
        List<Equipo> equipos = equipoRepository.findAll();
        EquipoDTO equipoDTO = new EquipoDTO();
        List<EquipoDTO> equiposDTOS = new ArrayList<EquipoDTO>();
        for (Equipo equipo: equipos) {
            equipoDTO.setNombre(equipo.getNombre());
            equipoDTO.setPais(equipo.getPais());
            equiposDTOS.add(equipoDTO);
            equipoDTO = new EquipoDTO();
        }
        return equiposDTOS;
    }

    public String crearEquipos(CuadraDTO cuadraDTO){
        Equipo equipoNuevo;
        if(cuadraDTO.getEquipos().size()==4){
            for (EquipoDTO equipoDTO: cuadraDTO.getEquipos()) {
                equipoNuevo = new Equipo();
                equipoNuevo.setNombre(equipoDTO.getNombre());
                equipoNuevo.setPais(equipoDTO.getPais());
                equipoRepository.save(equipoNuevo);
            }
            return "Cuadrangular creado";
        }else{
            return "No hay cuatro equipos exactos para el cuadrangular";
        }
    }

    public List<PartidoDTO> obtenerPartidos(){
        List<Partido> partidos = partidoRepository.findAll();
        PartidoDTO partidoDTO = new PartidoDTO();
        List<PartidoDTO> partidosDTOS = new ArrayList<PartidoDTO>();
        for (Partido partido: partidos) {
            partidoDTO.setIdEquipoLocal(partido.getIdEquipoLocal());
            partidoDTO.setIdEquipoVisitante(partido.getIdEquipoVisitante());
            partidoDTO.setGolesLocal(partido.getGolesLocal());
            partidoDTO.setGolesVisitante(partido.getGolesVisitante());
            partidosDTOS.add(partidoDTO);
            partidoDTO = new PartidoDTO();
        }
        return partidosDTOS;
    }

    public String definirMarcador(PartidoDTO partidoDTO) {
        if (partidoDTO.getIdEquipoLocal() == partidoDTO.getIdEquipoVisitante()) {
            return "No puede jugar un equipo contra si mismo";
        } else if (equipoRepository.findById(partidoDTO.getIdEquipoLocal()).isEmpty()
                || equipoRepository.findById(partidoDTO.getIdEquipoVisitante()).isEmpty()) {
            return "Id invalido no esta registrado";
        } else if(partidoDTO.getGolesLocal() < 0 || partidoDTO.getGolesVisitante() < 0) {
            return "Cantidad de goles invalida";

        }else{
            Partido partidoNuevo = new Partido();
            partidoNuevo.setIdEquipoLocal(partidoDTO.getIdEquipoLocal());
            partidoNuevo.setIdEquipoVisitante(partidoDTO.getIdEquipoVisitante());
            partidoNuevo.setGolesLocal(partidoDTO.getGolesLocal());
            partidoNuevo.setGolesVisitante(partidoDTO.getGolesVisitante());
            partidoRepository.save(partidoNuevo);
            return "partido creado";
        }
    }

    public List<ClasificacionDTO> mostrarResultados() {
        Clasificacion clasificacionNueva = new Clasificacion();
        ClasificacionDTO clasificacionNuevaDTO = new ClasificacionDTO();
        List<ClasificacionDTO> listaClasificacion = new ArrayList<ClasificacionDTO>();
        int cGoles = 0, cGolesContra = 0, cPartidosJugados = 0,
                cPartidosGanados = 0, cPartidosPerdidos = 0, cPartidosEmpatados = 0, puntos = 0;
        List<Partido> partidos = partidoRepository.findAll();
        for (Equipo equipo : equipoRepository.findAll()) {
            for (Partido partido : partidos) {
                if (partido.getIdEquipoLocal() == equipo.getId()) {
                    cPartidosJugados++;
                    cGoles += partido.getGolesLocal();
                    cGolesContra += partido.getGolesVisitante();
                    if (partido.getGolesLocal() < partido.getGolesVisitante()) {
                        cPartidosPerdidos++;
                    } else if (partido.getGolesLocal() == partido.getGolesVisitante()) {
                        cPartidosEmpatados++;
                    } else if (partido.getGolesLocal() > partido.getGolesVisitante()) {
                        cPartidosGanados++;
                    }
                }else if (partido.getIdEquipoVisitante() == equipo.getId()) {
                    cPartidosJugados++;
                    cGoles = partido.getGolesVisitante();
                    cGolesContra = partido.getGolesLocal();
                    if (partido.getGolesLocal() > partido.getGolesVisitante()) {
                        cPartidosPerdidos++;
                    } else if (partido.getGolesLocal() == partido.getGolesVisitante()) {
                        cPartidosEmpatados++;
                    } else if (partido.getGolesLocal() < partido.getGolesVisitante()) {
                        cPartidosGanados++;
                    }
                }
            }
            puntos = (cPartidosGanados*3) + (cPartidosEmpatados*1) + (cPartidosPerdidos*0);
            clasificacionNueva.setIdEquipo(equipo.getId());
            clasificacionNueva.setCantidadPuntos(puntos);
            clasificacionNueva.setCantidadGoles(cGoles);
            clasificacionNueva.setCantidadGolesContra(cGolesContra);
            clasificacionNueva.setCantidadPartidos(cPartidosJugados);
            clasificacionNueva.setCantidadPartidosGanados(cPartidosGanados);
            clasificacionNueva.setCantidadPartidosEmpate(cPartidosEmpatados);
            clasificacionNueva.setCantidadPartidosPerdidos(cPartidosPerdidos);
            clasificacionNuevaDTO.setIdEquipo(clasificacionNueva.getIdEquipo());
            clasificacionNuevaDTO.setCantidadPuntos(clasificacionNueva.getCantidadPuntos());
            clasificacionNuevaDTO.setCantidadGoles(clasificacionNueva.getCantidadGoles());
            clasificacionNuevaDTO.setCantidadGolesContra(clasificacionNueva.getCantidadGolesContra());
            clasificacionNuevaDTO.setCantidadPartidos(clasificacionNueva.getCantidadPartidos());
            clasificacionNuevaDTO.setCantidadPartidosEmpate(clasificacionNueva.getCantidadPartidosEmpate());
            clasificacionNuevaDTO.setCantidadPartidosGanados(clasificacionNueva.getCantidadPartidosGanados());
            clasificacionNuevaDTO.setCantidadPartidosPerdidos(clasificacionNueva.getCantidadPartidosPerdidos());
            listaClasificacion.add(clasificacionNuevaDTO);
            clasificacionRepository.save(clasificacionNueva);
            clasificacionNueva = new Clasificacion();
            clasificacionNuevaDTO = new ClasificacionDTO();
            cGoles = 0;
            cGolesContra = 0;
            cPartidosJugados = 0;
            cPartidosGanados = 0;
            cPartidosPerdidos = 0;
            cPartidosEmpatados = 0;
            puntos = 0;
        }
        return listaClasificacion;
    }
}
