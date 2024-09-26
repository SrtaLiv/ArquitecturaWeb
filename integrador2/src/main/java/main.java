import repositories.CarreraRepository;
import repositories.EstudianteRepository;
import service.Servicios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        Servicios servicios = new Servicios(em);
//        servicios.inicializarDB();
//        servicios.agregarEstudiante();
//        servicios.matricularEstudiante();
//        servicios.obtenerEstudiantes();
//        servicios.obtenerEstudiantePorLU();
//        servicios.obtenerEstudiantesPorGenero();
//        servicios.obteberCarrerasConInscriptos();
//        servicios.obtenerEstudiantesPorCarreraCiudad();
        servicios.generarReporte();
        em.close();
        emf.close();
    }
}
