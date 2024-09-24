import entities.Carrera;
import entities.Estudiante;
import entities.Estudiante_Carrera;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Carrera carrera = new Carrera();
        em.persist(carrera);
        Estudiante juliso = new Estudiante(251950, "julieta", "sosa", 21, "femenino", 44321795, "Tandil");
        em.persist(juliso);
        Estudiante tomi= new Estudiante(251096, "tomas", "quinteros", 21, "masculino", 43, "Tandil");
        em.persist(tomi);

        em.getTransaction().commit();
        em.close();
        emf.close();


    }
}
