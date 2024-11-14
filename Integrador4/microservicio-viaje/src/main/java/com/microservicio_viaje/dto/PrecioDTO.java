package com.microservicio_viaje.dto;


import com.microservicio_viaje.entity.Precio;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class PrecioDTO  implements Serializable {
    private Long id;

    private double valor;
    private LocalDate fechaInicio;

    private Double valorPorPausaExtendida;


    public PrecioDTO(Long id, String clave, double valor, LocalDate fechaInicio, Double valorPorPausaExtendida) {
        this.id = id;
        this.valor = valor;
        this.fechaInicio = fechaInicio;
        this.valorPorPausaExtendida=valorPorPausaExtendida;
    }

    public PrecioDTO(Precio p){
        this.id = p.getId();
        this.valor = p.getValor();
        this.valorPorPausaExtendida= p.getValorPorPausaExtendida();
    }

    public Long getId() {
        return id;
    }

    public Double getValorPorPausaExtendida() {
        return valorPorPausaExtendida;
    }

    public void setValorPorPausaExtendida(Double valorPorPausaExtendida) {
        this.valorPorPausaExtendida = valorPorPausaExtendida;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
}
