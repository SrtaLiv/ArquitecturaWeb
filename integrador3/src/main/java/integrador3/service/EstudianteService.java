package integrador3.service;

import integrador3.entities.Carrera;
import integrador3.entities.Estudiante;
import integrador3.repository.EstudianteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("EstudianteService")
public class EstudianteService implements Servicio<Estudiante>{

    private static final Logger log = LoggerFactory.getLogger(EstudianteService.class);
    @Autowired
    private EstudianteRepository repository;

    @Override
    public List<Estudiante> findAll() throws Exception {
        try {
            return repository.findAll();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Estudiante save(Estudiante entity) throws Exception {
        try {
            return repository.save(entity);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Estudiante update(int id, Estudiante entity) throws Exception {
        //TODO
        return null;
    }

    @Override
    public boolean delete(int id) throws Exception {
        //TODO
        return false;
    }

    @Override
    public Estudiante update(int id, Carrera entity) throws Exception {
        //TODO
        return null;
    }

    public Estudiante findById(int id) throws Exception {
        try{
            Optional<Estudiante> estudiante = repository.findById(id);
            return estudiante.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Estudiante findByLU(int lu) throws Exception {
        try{
            Optional<Estudiante> estudiante = repository.findByLU(lu);
            return estudiante.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Estudiante> findByGenero(String genero) throws Exception {
        try{
            List<Estudiante> estudiante = repository.findByGenero(genero);
            return estudiante;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Estudiante> findByCarreraCiudad(int carrera, String ciudad) throws Exception {
        try{
            List<Estudiante> estudiante = repository.findByCarreraCiudad(carrera, ciudad);
            return estudiante;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
