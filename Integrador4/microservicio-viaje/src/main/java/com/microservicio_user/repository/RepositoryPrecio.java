package com.microservicio_user.repository;

import com.microservicio_user.model.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPrecio extends JpaRepository<Precio, Long> {
}
