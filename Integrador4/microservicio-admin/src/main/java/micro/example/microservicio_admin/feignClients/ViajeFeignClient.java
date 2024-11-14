package micro.example.microservicio_admin.feignClients;

import micro.example.microservicio_admin.dto.MonopatinViajeDTO;
import micro.example.microservicio_admin.dto.ReporteKilometrajeDTO;
import micro.example.microservicio_admin.entity.clases.Monopatin;
import micro.example.microservicio_admin.entity.clases.Precio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="microservicio-viaje")
public interface ViajeFeignClient {

    @PostMapping("/precios/agregar")
    ResponseEntity<?> agregarPrecio(@RequestBody Precio p);

    @GetMapping("/{cantidad}/{anio}")
    ResponseEntity<List<MonopatinViajeDTO>> findMonopatinesConMasDeXViajesPorAnio(@PathVariable int cant, @PathVariable int anio);

    @GetMapping("/viajes/getReporteKilometraje/{limite}/{incluirPausas}")
    ResponseEntity<List<ReporteKilometrajeDTO>> getReporteKilometraje(@PathVariable Long limite, @PathVariable boolean incluirPausas);

    @GetMapping("/viajes/facturado/{anio}/{mesInicio}/{mesFin}")
    ResponseEntity<Integer> getTotalFacturadoEntreMeses(@PathVariable int anio, @PathVariable int mesInicio, @PathVariable int mesFin);

}
