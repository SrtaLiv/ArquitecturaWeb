package com.microservicio_parada.microservicio_parada.repository;

import com.microservicio_parada.microservicio_parada.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryViaje extends JpaRepository<Viaje, Long> {
}
