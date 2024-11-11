package com.microservicio_monopatin.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Monopatin {

    private int latitud;
    private int longitud;
    private boolean estado;
    @Id
    @GeneratedValue()
    private Long id;

    public Monopatin(int latitud, int longitud, boolean estado, Long id) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.id = id;
    }

    public Monopatin() {

    }
}
