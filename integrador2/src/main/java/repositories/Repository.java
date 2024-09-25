package repositories;

import javax.persistence.EntityManager;

public interface Repository <T>{

    void create(EntityManager em, T object);
    void update(EntityManager em, T object);
    void delete(EntityManager em, int id);
    T findById(EntityManager em, int id);
}