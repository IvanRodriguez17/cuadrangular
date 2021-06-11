package com.example.cuadrangular.repositories;

import com.example.cuadrangular.entities.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
    public Partido getPartidoById(long id);

}
