package integrador3.integrador3.controllers;

import integrador3.integrador3.entities.Estudiante;
import integrador3.integrador3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estudiante")
public class EstudianteController {
    @Autowired
    private final EstudianteRepository repository;

    public EstudianteController(EstudianteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Iterable<Estudiante> estudiantes() {
        return repository.findAll();
    }
}
