package com.microservicio_viaje.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Data
public class Precio {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column
    private String clave;

    @Setter
    @Getter
    @Column
    private double valor;

    @Column
    private LocalDate fechaFacturacion;

    @Setter
    @Getter
    @Column
    private Double valorPorPausaExtendida;

    @JsonIgnore
    @OneToOne(mappedBy = "precio")
    private Viaje viaje;



    public Precio(Long id, String clave, double valor, LocalDate fechaFacturacion, Viaje viaje) {
        this.id = id;
        this.clave = clave;
        this.valor = valor;
        this.fechaFacturacion = fechaFacturacion;
        this.valorPorPausaExtendida = 0.0;
        this.viaje = viaje;
    }

    public Precio() {

    }

    public Precio(Precio precio) {
        this.id = precio.id;
        this.clave = precio.clave;
        this.valor = precio.valor;
        this.fechaFacturacion = precio.fechaFacturacion;
        this.valorPorPausaExtendida = precio.valorPorPausaExtendida;
        this.viaje = precio.viaje;
    }

    public Precio(Long id, String clave, double valor, LocalDate fechaFacturacion, Double valorPorPausaExtendida, Viaje viaje) {
        this.id = id;
        this.clave = clave;
        this.valor = valor;
        this.fechaFacturacion = fechaFacturacion;
        this.valorPorPausaExtendida = valorPorPausaExtendida;
        this.viaje = viaje;
    }

    public LocalDate getFechaInicio() {
        return fechaFacturacion;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaFacturacion = fechaInicio;
    }
}
