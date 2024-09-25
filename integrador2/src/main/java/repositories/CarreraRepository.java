package repositories;

import entities.Carrera;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CarreraRepository implements Repository<Carrera>{
    @Override
    public void create(EntityManager em, Carrera object) {
        //TODO
    }

    @Override
    public void update(EntityManager em, Carrera object) {
        //TODO
    }

    @Override
    public void delete(EntityManager em, int id) {
        //TODO
    }

    @Override
    public Carrera findById(EntityManager em, int id) {
        //TODO
        return null;
    }

    public List<Carrera> findCarreraConInscriptos(EntityManager em) {
        String jpql = "SELECT c FROM Carrera c WHERE c.estudiantes.size > 0 ORDER BY c.estudiantes.size DESC ";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
}
