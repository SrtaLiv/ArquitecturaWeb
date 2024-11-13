package com.microservicio_viaje.repository;

import com.microservicio_viaje.dto.MonopatinViajeDTO;
import com.microservicio_viaje.dto.ReporteKilometrajeDTO;
import com.microservicio_viaje.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryViaje extends JpaRepository<Viaje, Long> {

    @Query("SELECT new com.microservicio_viaje.dto.MonopatinViajeDTO(v.idMonopatin, COUNT(v)) FROM Viaje v WHERE FUNCTION('YEAR', v.fecha) = :anio GROUP BY v.idMonopatin HAVING COUNT(v) > :cantidad")
    List<MonopatinViajeDTO> findMonopatinesConMasDeXViajesPorAnio(@Param("cantidad") int cantidad, @Param("anio") int anio);

    @Query("select NEW com.microservicio_viaje.dto.ReporteKilometrajeDTO(v.idMonopatin,sum(v.kmRecorridos)) from Viaje v where v.kmRecorridos >= :umbral group by v.idMonopatin")
    List<ReporteKilometrajeDTO> getReporteKilometraje(long umbral);


    @Query("select NEW com.microservicio_viaje.dto.ReporteKilometrajeDTO(v.idMonopatin,sum(v.kmRecorridos),sum(p.pausaTotal)) from Viaje v join Pausa p on v.id=p.id where v.kmRecorridos >= :umbral group by v.idMonopatin")
    List<ReporteKilometrajeDTO> getReporteKilometrajeConPausas(long umbral);

    @Query("SELECT SUM(p.valor + p.valorPorPausaExtendida) FROM Viaje v JOIN v.precio p WHERE FUNCTION('YEAR', v.fecha) = :anio AND FUNCTION('MONTH', v.fecha) BETWEEN :mesInicio AND :mesFin")
    Integer getFacturadoEntreMeses(int anio, int mesInicio, int mesFin);
}