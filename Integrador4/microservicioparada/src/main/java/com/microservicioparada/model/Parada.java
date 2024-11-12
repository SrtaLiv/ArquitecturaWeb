package com.microservicio_parada.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity @Getter
@Setter
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long x;
    private Long y;

    // Lista de IDs de Monopatines en lugar de la entidad completa
    @ElementCollection
    private List<Long> monopatinIds;

    public Parada(Parada parada) {
        this.id = parada.id;
        this.x = parada.x;
        this.y = parada.y;
        this.monopatinIds = parada.monopatinIds;
    }

    public Parada(Long x, Long y, List<Long> monopatinIds) {
        this.x = x;
        this.y = y;
        this.monopatinIds = monopatinIds;
    }
    public Parada() {}


}
