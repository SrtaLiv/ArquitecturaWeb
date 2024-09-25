import entities.Carrera;
import entities.Estudiante;
import helper.HelperMYSQL;
import repositories.CarreraRepository;
import repositories.EstudianteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        HelperMYSQL helperMYSQL = new HelperMYSQL(em);
//        helperMYSQL.generarDatos();
        EstudianteRepository er = new EstudianteRepository();
        System.out.println(er.findByCarreraAndCiudad(em, 1, "Pehuajo"));
        CarreraRepository cr = new CarreraRepository();
//        System.out.println(cr.findCarreraConInscriptos(em));
        em.close();
        emf.close();
    }
}
