package integrador3.repository;


import integrador3.DTO.CarreraDTO;
import integrador3.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    //F
  //  @Query("SELECT c, c.estudiantes.size FROM Carrera c WHERE c.estudiantes.size > 0 ORDER BY c.estudiantes.size DESC")
    @Query("SELECT c FROM Carrera c WHERE c.estudiantes IS NOT EMPTY")
    public List<CarreraDTO> findCarreraConInscriptos();
}
