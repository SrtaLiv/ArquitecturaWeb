package micro.example.microservicio_admin.feignClients;

import micro.example.microservicio_admin.entity.clases.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="microservicio-monopatin")
public interface MonopatinFeignClient {

        @GetMapping("monopatines/{id}")
        Monopatin getMonopatinById(@PathVariable Long id);

        @PostMapping("monopatines/editar/{id}")
        ResponseEntity<Monopatin> updateMonopatin(@PathVariable Long id, Monopatin monopatin);

}


