import entities.Carrera;
import entities.Estudiante;
import helper.HelperMYSQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        HelperMYSQL helperMYSQL = new HelperMYSQL(em);
        helperMYSQL.generarDatos();

        em.close();
        emf.close();
    }
}
