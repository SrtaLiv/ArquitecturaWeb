package integrador3.integrador3.utils;

import integrador3.integrador3.entities.Estudiante;
import integrador3.integrador3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
/*
    @Bean
    CommandLineRunner initDatabase(@Qualifier("estudianteRepository") EstudianteRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Estudiante((int) 1234,"Olivia", "Todesco",19, "Femenino", 12, "Olavarria")));
        };
    }*/
}