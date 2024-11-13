package com.microservicio_viaje.repository;

import com.microservicio_viaje.entity.Viaje;
import com.microservicio_viaje.entity.clases.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryViaje extends JpaRepository<Viaje, Long> {

    // Seleccionar los viajes en esa fecha y que el monopatín tenga más de X viajes
    @Query("SELECT v FROM Viaje v WHERE YEAR(v.fecha) = :anio AND v.idMonopatin IN (" +
            "SELECT v2.idMonopatin FROM Viaje v2 WHERE YEAR(v2.fecha) = :anio " +
            "GROUP BY v2.idMonopatin HAVING COUNT(v2) > :cantidad)")
    List<Monopatin> findMonopatinesConMasDeXViajesPorAnio(@Param("cantidad") int x, @Param("anio") int anio);

}