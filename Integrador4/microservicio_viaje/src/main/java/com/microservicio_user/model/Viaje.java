package com.microservicio_user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

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
