package service;

import entities.Carrera;
import entities.Estudiante;
import entities.Estudiante_Carrera;
import helper.CSVReader;
import repositories.CarreraRepository;
import repositories.EstudianteRepository;
import repositories.Estudiante_CarreraRepository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class Servicios {
    private EntityManager em;
    private CarreraRepository cr;
    private EstudianteRepository er;
    private Estudiante_CarreraRepository ecr;

    public Servicios(EntityManager em){
        this.em = em;
        this.cr = new CarreraRepository(em);
        this.er = new EstudianteRepository(em);
        this.ecr = new Estudiante_CarreraRepository(em);
    }

    public void inicializarDB() throws Exception {
        CSVReader reader = new CSVReader(em, cr, ecr, er);
        reader.populateDB();
    }
    //a
    public void agregarEstudiante(Estudiante estudiante){
        er.create(estudiante);
    }
    //b
    public void matricularEstudiante(Estudiante estudiante, Carrera carrera){
        Estudiante_Carrera estudianteCarrera = new Estudiante_Carrera(estudiante, carrera, new Date(), null);
        ecr.create(estudianteCarrera);
    }
    //c
    public List<Estudiante> obtenerEstudiantes(){
        return er.findAll();
    }
    //d
    public Estudiante obtenerEstudiantePorLU(int LU){
        return er.findById(LU);
    }
    //e
    public List<Estudiante> obtenerEstudiantesPorGenero(String genero){
        return er.findByGenero(genero);
    }
    //f
    public List<Carrera> obteberCarrerasConInscriptos(){
        return cr.findCarreraConInscriptos();
    }
    //g
    public List<Estudiante> obtenerEstudiantesPorCarreraCiudad(int id_carrera, String ciudad){
        return er.findByCarreraAndCiudad(id_carrera, ciudad);
    }
    //3
    public List<Carrera> generarReporte(){
        return null;
    }

}
