package integrador3.integrador3.repository;


import integrador3.integrador3.entities.Estudiante;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository //no se pq se importa asi .-.
public interface EstudianteRepository extends Repository<Estudiante, Long> {

}
