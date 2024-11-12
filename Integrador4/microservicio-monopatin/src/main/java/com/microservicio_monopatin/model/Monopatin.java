package com.microservicio_monopatin.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Monopatin {

   /* private int latitud;
    private int longitud;
    private boolean estado;*/

    private String numeroSerie;
    private double kilometraje;
    private boolean enMantenimiento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    public Monopatin(int latitud, int longitud, boolean estado) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
    }*/

    public Monopatin(String numeroSerie, double kilometraje, boolean enMantenimiento) {
        this.numeroSerie = numeroSerie;
        this.kilometraje = kilometraje;
        this.enMantenimiento = enMantenimiento;
    }

    public Monopatin() {

    }

    public boolean isEnMantenimiento() {
        return enMantenimiento;
    }

    public void setEnMantenimiento(boolean enMantenimiento) {
        this.enMantenimiento = enMantenimiento;
    }
}
