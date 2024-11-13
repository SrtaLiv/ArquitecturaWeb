package com.microservicio_viaje.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class MonopatinViajeDTO {
    //List<Long> id_monopatin;
    Long id_monopatin;
    Long cantidad;

    public MonopatinViajeDTO(Long id_monopatin, Long cantidad) {
        this.id_monopatin = id_monopatin;
        this.cantidad = cantidad;
    }

}
