package micro.example.microservicio_admin.feignClients;

import micro.example.microservicio_admin.entity.clases.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservicio_monopatin")
public interface MonopatinFeignClient {

        @GetMapping("monopatines/{id}")
        Monopatin getMonopatinById(@PathVariable Long id);

}


