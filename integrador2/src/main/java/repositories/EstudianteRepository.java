package repositories;

import entities.Estudiante;

import javax.persistence.EntityManager;

public class EstudianteRepository implements Repository<Estudiante>{
    @Override
    public void create(EntityManager em, Estudiante object) {
        //TODO
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
        //TODO
        return null;
    }
}
