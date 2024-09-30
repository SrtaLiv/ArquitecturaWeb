package repositories;

import DTO.CarreraInfoDTO;
import DTO.ReporteDTO;
import entities.Carrera;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
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

    public List<ReporteDTO> generarReporte() {
        List<Carrera> carreras = em.createQuery("SELECT c FROM Carrera c ORDER BY c.nombre", Carrera.class).getResultList();
        List<ReporteDTO> reportes = new ArrayList<>();

        for(Carrera carrera : carreras){
            ReporteDTO reporte = new ReporteDTO(carrera.getNombre());

            String jpql = "SELECT YEAR(ec.fecha_inicio), COUNT(ec), " +
                    "SUM(CASE WHEN ec.fecha_fin IS NOT NULL THEN 1 ELSE 0 END) " +
                    "FROM Estudiante_Carrera ec " +
                    "WHERE ec.carrera.id_carrera = :idCarrera " +
                    "GROUP BY YEAR(ec.fecha_inicio) " +
                    "ORDER BY YEAR(ec.fecha_inicio)";

            List<Object[]> resultados = em.createQuery(jpql).setParameter("idCarrera", carrera.getId_carrera()).getResultList();
            for (Object[] resultado : resultados){
                int anio = (Integer) resultado[0];
                int inscriptos = ((Number) resultado[1]).intValue();
                int egresados = ((Number) resultado[2]).intValue();

                reporte.getInfoPorAnio().put(anio, new CarreraInfoDTO(inscriptos, egresados));
            }

            reportes.add(reporte);
        }
        return reportes;
    }
}
