package com.microservicio_user.repository;

import com.microservicio_user.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryViaje extends JpaRepository<Viaje, Long> {
}
