package com.microservicio_viaje.dto;

import com.microservicio_viaje.entity.Precio;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class PrecioFechaDTO  implements Serializable {
    private Long id;

    private double valor;
    private LocalDate fechaInicio;


    public PrecioFechaDTO(Long id, double valor, LocalDate fechaInicio) {
        this.id = id;
        this.valor = valor;
        this.fechaInicio = fechaInicio;
    }

    public PrecioFechaDTO(Precio p){
        this.id = p.getId();
        this.valor = p.getValor();
        this.fechaInicio = p.getFechaInicioAHabilitar();
    }

    public Long getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
}
