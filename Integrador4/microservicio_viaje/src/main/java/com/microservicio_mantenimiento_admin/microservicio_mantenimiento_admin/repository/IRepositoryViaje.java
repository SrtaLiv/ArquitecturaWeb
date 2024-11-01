package com.microservicio_mantenimiento_admin.microservicio_mantenimiento_admin.repository;

import com.microservicio_mantenimiento_admin.microservicio_mantenimiento_admin.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryViaje extends JpaRepository<Viaje, Long> {
}
