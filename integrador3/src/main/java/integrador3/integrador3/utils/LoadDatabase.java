package integrador3.integrador3.utils;

import integrador3.integrador3.entities.Estudiante;
import integrador3.integrador3.repository.EstudianteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(
            @Qualifier("estudianteRepository") EstudianteRepository repository
    ) {
        return args -> {
            Estudiante e = new Estudiante(12345678, "Olivia", "Todesco", 19, "Femenino", 123456, "Tandil");
            log.info("Preloading " + repository.save(e));
        };
    }
}