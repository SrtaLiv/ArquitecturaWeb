package integrador3.repository;


import integrador3.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

  //  Object getEstudiantesPorAtributoOrderByEdadAsc(String atributo);
}
