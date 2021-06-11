package com.example.cuadrangular.repositories;

import com.example.cuadrangular.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    public Equipo getEquipoById(long id);
}
