package micro.example.microservicio_admin.feignClients;

import micro.example.microservicio_admin.entity.clases.Monopatin;
import micro.example.microservicio_admin.entity.clases.Parada;
import micro.example.microservicio_admin.service.dto.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="microservicio-parada")
public interface ParadaFeignClient {

        @GetMapping("paradas/{id}")
        Parada getParadaById(@PathVariable Long id);

        @PostMapping("paradas/editar/{id}")
        ResponseEntity<Parada> updateParada(@PathVariable Long id, Parada parada);

        @PostMapping("paradas")
        ResponseEntity<Parada> saveParada(@RequestBody Parada parada); //DTO?

        @DeleteMapping("paradas/{id}")
        ResponseEntity<Void> deleteParada(@PathVariable Long id);

}
