package com.microservicio_viaje.repository;

import com.microservicio_viaje.model.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPrecio extends JpaRepository<Precio, Long> {
}
