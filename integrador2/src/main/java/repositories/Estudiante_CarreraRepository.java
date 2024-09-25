package repositories;

import com.sun.xml.bind.v2.TODO;
import entities.Estudiante_Carrera;
import javax.persistence.EntityManager;

public class Estudiante_CarreraRepository implements Repository<Estudiante_Carrera>{

    @Override
    public void create(EntityManager em, Estudiante_Carrera object) {
        em.persist(object);
    }

    @Override
    public void update(EntityManager em, Estudiante_Carrera object) {
        //TODO
    }

    @Override
    public void delete(EntityManager em, int id) {
        //TODO
    }

    @Override
    public Estudiante_Carrera findById(EntityManager em, int id) {
        return em.find(Estudiante_Carrera.class, id);
    }

}
