package repositories;

import entities.Carrera;
import entities.Estudiante;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CarreraRepository implements Repository<Carrera>{
    private EntityManager em;
    public CarreraRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Carrera object) {
        em.persist(object);
    }

    @Override
    public void update(Carrera object) {
        //TODO
    }

    @Override
    public void delete(int id) {
        //TODO
    }

    @Override
    public Carrera findById(int id) {
        return em.find(Carrera.class, id);
    }

    public List<Carrera> findCarreraConInscriptos() {
        String jpql = "SELECT c FROM Carrera c WHERE c.estudiantes.size > 0 ORDER BY c.estudiantes.size DESC ";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    public List<Carrera> generarReporte() {
        String jpql = "SELECT c FROM Carrera c order by c.nombre ASC";
        List<Carrera>carreras = em.createQuery(jpql).getResultList();
        EstudianteRepository er = new EstudianteRepository(em);
        for(Carrera c : carreras){
            System.out.println(c.getNombre());
            List<Estudiante> inscriptos = er.findEstudiantesReporte(c.getId_carrera(), false);
            System.out.println("inscriptos\n");
            System.out.println(inscriptos);
            List<Estudiante> egresados = er.findEstudiantesReporte(c.getId_carrera(), true);
            System.out.println("egresados\n");
            System.out.println(egresados);
            //TODO
            // crear el DTO,
            // hacer la query de los inscriptos por años
            // agregar esa lista al DTO
            // lo mismo con los egresados
        }
        return  null;
    }
}
