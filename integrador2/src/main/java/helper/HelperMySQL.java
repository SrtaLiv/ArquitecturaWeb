package helper;

import entities.Carrera;
import entities.Estudiante;
import entities.Estudiante_Carrera;

import javax.persistence.EntityManager;
import java.util.Date;


public class HelperMySQL {
    private EntityManager em;

    public HelperMySQL(EntityManager em){
        this.em = em;
    }

    public void generarDatos(){
        em.getTransaction().begin();
        Estudiante tomi= new Estudiante(123456, "tomas", "quinteros", 21, "masculino", 41234567, "Tandil");
        Estudiante oli= new Estudiante(234567, "olivia", "todesco", 21, "femenino", 42345678, "Tandil");
        Estudiante juli= new Estudiante(345678, "julieta", "sosa", 21, "femenino", 43456789, "Pehuajo");
        em.persist(tomi);
        em.persist(oli);
        em.persist(juli);
        Carrera medicina = new Carrera(1, "MEDICINA");
        Carrera tudai = new Carrera(2, "TUDAI");
        Carrera administracion = new Carrera(3, "ADMINISTRACION");
        em.persist(medicina);
        em.persist(tudai);
        em.persist(administracion);
        Date inicio = new Date(1692710400000L);
        Date fin = new Date(1692710400000L);

        Estudiante_Carrera ec = new Estudiante_Carrera(tomi, medicina, inicio, fin);
        Estudiante_Carrera ec2= new Estudiante_Carrera(oli, medicina, inicio, fin);
        Estudiante_Carrera ec3 = new Estudiante_Carrera(juli, medicina, inicio, null);
        Estudiante_Carrera ec4 = new Estudiante_Carrera(tomi, tudai, inicio, fin);

        em.persist(ec);
        em.persist(ec2);
        em.persist(ec3);
        em.persist(ec4);

        em.getTransaction().commit();
    }

//    Carrera carrera = new Carrera();
//    Estudiante juliso = new Estudiante(251950, "julieta", "sosa", 21, "femenino", 44321795, "Tandil");
//    Estudiante tomi= new Estudiante(251096, "tomas", "quinteros", 21, "masculino", 43, "Tandil");
}
