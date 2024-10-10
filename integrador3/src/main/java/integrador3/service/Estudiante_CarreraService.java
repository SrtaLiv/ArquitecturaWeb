package integrador3.service;

import integrador3.repository.EstudianteRepository;
import integrador3.repository.Estudiante_CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("Estudiante_CarreraService")
public class Estudiante_CarreraService {
    @Autowired
    private Estudiante_CarreraRepository estudianteCarreraRepository;
}
