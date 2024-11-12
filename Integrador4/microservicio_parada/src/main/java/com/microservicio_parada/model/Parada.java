package com.microservicio_parada.model;

import com.microservicio_parada.model.clases.Monopatin;
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

    @OneToMany(mappedBy = "parada") // Mapea la relación con el atributo "parada" en Monopatin
    private List<Monopatin> monopatinesEnLaParada;
}
