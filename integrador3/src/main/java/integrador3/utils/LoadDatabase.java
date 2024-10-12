package integrador3.utils;

import integrador3.repository.CarreraRepository;
import integrador3.repository.EstudianteRepository;
import integrador3.repository.Estudiante_CarreraRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(
            @Qualifier("carreraRepository") CarreraRepository carreraRepository,
            @Qualifier("estudianteRepository") EstudianteRepository estudianteRepository,
            @Qualifier("estudiante_CarreraRepository") Estudiante_CarreraRepository estudiante_carreraRepository
            ) {
        return args -> {
            CSVReader reader = new CSVReader(carreraRepository, estudianteRepository, estudiante_carreraRepository);
            reader.populateDB();
        };
    }
}