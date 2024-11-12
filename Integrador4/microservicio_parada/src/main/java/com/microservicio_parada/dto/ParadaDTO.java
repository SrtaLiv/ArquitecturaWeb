package com.microservicio_parada.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParadaDTO {
    private Long x;
    private Long y;
    private List<Long> monopatinIds;
}
