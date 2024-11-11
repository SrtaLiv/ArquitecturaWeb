package com.microservicio_monopatin.repository;

import com.microservicio_monopatin.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryViaje extends JpaRepository<Viaje, Long> {
}
