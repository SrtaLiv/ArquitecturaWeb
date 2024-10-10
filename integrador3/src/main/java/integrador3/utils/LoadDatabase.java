package integrador3.utils;

import integrador3.entities.Carrera;
import integrador3.entities.Estudiante;
import integrador3.repository.CarreraRepository;
import integrador3.repository.EstudianteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {
/*
    @Bean
    CommandLineRunner initDatabase(
            @Qualifier("carreraRepository") CarreraRepository carreraRepo
    ) {
        return args -> {
            Carrera c1 = new Carrera(1, "Bien", 11);
            Carrera c2 = new Carrera(2, "Bien2", 11);
            log.info("Preloading " + carreraRepo.save(c1));
            log.info("Preloading " + carreraRepo.save(c2));
        };
    }*/
}