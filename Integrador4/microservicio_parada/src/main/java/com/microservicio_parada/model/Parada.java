package com.microservicio_parada.model;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter
@Setter
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int latitud;
    private int longitud;
    private int monopatin_id;
    private int km_acumulados;

    public Parada(Long id, int latitud, int longitud, int monopatin_id, int km_acumulados) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.monopatin_id = monopatin_id;
        this.km_acumulados = km_acumulados;
    }

    public Parada() {

    }
}
