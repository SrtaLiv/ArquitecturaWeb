package com.microservicio_viaje.repository;

import com.microservicio_viaje.entity.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositoryPrecio extends JpaRepository<Precio, Long> {
    @Query("SELECT p FROM Precio p WHERE p.fechaFacturacion > :fechaAHabilitar")
    List<Precio> findByFechaFacturacionAfter(@Param("fechaAHabilitar") LocalDate fechaAHabilitar);

}

