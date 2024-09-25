package repositories;

import entities.Carrera;

import javax.persistence.EntityManager;

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
}
