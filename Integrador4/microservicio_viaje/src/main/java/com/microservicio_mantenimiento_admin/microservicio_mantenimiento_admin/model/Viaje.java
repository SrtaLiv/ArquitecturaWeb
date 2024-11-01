package com.microservicio_mantenimiento_admin.microservicio_mantenimiento_admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter @Setter
public class Viaje {

    @Id
    private Long id;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int kmRecorridos;
    private boolean pausa;

    private int idMonopatin;
    private int idUsuario;

}
