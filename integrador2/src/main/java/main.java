import entities.Estudiante;
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
//        Estudiante estudiante = new Estudiante(999999, "Pedro", "Sanchez", 99, "Masculino", 99999999, "Paris");
//        servicios.agregarEstudiante(estudiante);
//        servicios.matricularEstudiante(999999, 1);
//        System.out.println(servicios.obtenerEstudiantes());
//        System.out.println(servicios.obtenerEstudiantePorLU(101112));
//        System.out.println(servicios.obtenerEstudiantesPorGenero("femenino"));
//        System.out.println(servicios.obteberCarrerasConInscriptos());
//        System.out.println(servicios.obtenerEstudiantesPorCarreraCiudad(1, "Buenos Aires"));
//        System.out.println(servicios.generarReporte());
        em.close();
        emf.close();
    }
}
