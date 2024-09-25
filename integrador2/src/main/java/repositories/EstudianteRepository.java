package repositories;

import entities.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EstudianteRepository implements Repository<Estudiante>{
    @Override
    public void create(EntityManager em, Estudiante object) {
        em.persist(object);
    }

    @Override
    public void update(EntityManager em, Estudiante object) {
        //TODO
    }

    @Override
    public void delete(EntityManager em, int id) {
        //TODO
    }

    @Override
    public Estudiante findById(EntityManager em, int id) {
        return em.find(Estudiante.class, id);
    }

    public List<Estudiante> findAll(EntityManager em){
        String jpql = "SELECT e FROM Estudiante e ORDER BY e.apellido";
        return  em.createQuery(jpql, Estudiante.class).getResultList();
    }

    public List<Estudiante> findByGenero(EntityManager em, String genero){
        String jpql = "SELECT e FROM Estudiante e WHERE e.genero = :genero";
        Query q = em.createQuery(jpql, Estudiante.class);
        q.setParameter("genero", genero);
        return q.getResultList();
    }

    public List<Estudiante> findByCarreraAndCiudad(EntityManager em,int id_carrera,String ciudad) {
        String jpql = "SELECT e FROM Estudiante e JOIN Estudiante_Carrera ec ON ec.estudiante.nroLU = e.nroLU WHERE ec.carrera.id_carrera = :carrera AND e.ciudadResidencia = :ciudad";
        Query query = em.createQuery(jpql);
        query.setParameter("carrera", id_carrera);
        query.setParameter("ciudad", ciudad);
        return query.getResultList();
    }
}
