package integrador3.repository;


import integrador3.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    @Query("SELECT e FROM Estudiante e WHERE e.nroLU = :lu")
    public Optional<Estudiante> findByLU(int lu);

    @Query("SELECT e FROM Estudiante e WHERE e.genero = :genero")
    public List<Estudiante> findByGenero(String genero);

    //  Object getEstudiantesPorAtributoOrderByEdadAsc(String atributo);
}
