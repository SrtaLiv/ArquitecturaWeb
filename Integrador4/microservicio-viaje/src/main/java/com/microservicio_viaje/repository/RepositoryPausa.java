package com.microservicio_viaje.repository;


import com.microservicio_viaje.entity.Pausa;
import com.microservicio_viaje.entity.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositoryPausa extends JpaRepository<Pausa, Long> {

}
