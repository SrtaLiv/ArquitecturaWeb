package micro.example.microservicio_admin.feignClients;

import micro.example.microservicio_admin.entity.clases.Monopatin;
import micro.example.microservicio_admin.entity.clases.Precio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="microservicio-viaje")
public interface ViajeFeignClient {

   /* @GetMapping("/viajes/{id}")
    ResponseEntity<Viaje> getViajeById(@PathVariable Long id);*/

    @PostMapping("/precios/agregar")
    ResponseEntity<?> agregarPrecio(@RequestBody Precio p);

    ResponseEntity<List<Monopatin>> findMonopatinesConMasDeXViajesPorAnio(@PathVariable int cant, @PathVariable int anio);

}
