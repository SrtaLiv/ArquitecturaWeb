package micro.example.microservicio_admin.feignClients;

import micro.example.microservicio_admin.entity.clases.Monopatin;
import micro.example.microservicio_admin.service.dto.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="microservicio-monopatin")
public interface MonopatinFeignClient {

        @GetMapping("monopatines/{id}")
        Monopatin getMonopatinById(@PathVariable Long id);

        @PostMapping("monopatines/editar/{id}")
        ResponseEntity<Monopatin> updateMonopatin(@PathVariable Long id, Monopatin monopatin);

        @PostMapping("monopatines")
        ResponseEntity<MonopatinDTO> saveMonopatin(@RequestBody MonopatinDTO monopatin);
}

//        ResponseEntity<Void> deleteMonopatin(@PathVariable("id") Long id);}


