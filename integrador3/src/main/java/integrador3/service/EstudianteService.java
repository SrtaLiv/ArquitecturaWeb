package integrador3.service;

import integrador3.entities.Estudiante;
import integrador3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("EstudianteService")
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante findById(int id) throws Exception {
        try{
            Optional<Estudiante> estudiante = estudianteRepository.findById(id);
            return estudiante.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
