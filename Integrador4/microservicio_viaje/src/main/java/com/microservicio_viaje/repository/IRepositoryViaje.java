package com.microservicio_viaje.repository;

import com.microservicio_viaje.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryViaje extends JpaRepository<Viaje, Long> {
}
