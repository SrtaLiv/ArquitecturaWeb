import helper.HelperMySQL;
import helper.CSVReader;
import repositories.CarreraRepository;
import repositories.EstudianteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class main {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
//        HelperMySQL helperMYSQL = new HelperMySQL(em);
//        helperMYSQL.generarDatos();
        EstudianteRepository er = new EstudianteRepository();
//        System.out.println(er.findByCarreraAndCiudad(em,1,  "Tandil"));
        CarreraRepository cr = new CarreraRepository();
//        System.out.println(cr.generarReporte(em));
        CSVReader reader = new CSVReader(em);
        reader.populateDB();
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
