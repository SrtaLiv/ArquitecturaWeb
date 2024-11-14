package com.microservicio_viaje.feignClients;

import com.microservicio_viaje.services.dto.ParadaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name="microservicioparada")
public interface ParadaFeignClient {

    @GetMapping("/paradas/monopatinesCercanos/{x}/{y}")
    ResponseEntity<List<ParadaDTO>> getMonopatinesCercanos(@PathVariable double x, @PathVariable double y);
}