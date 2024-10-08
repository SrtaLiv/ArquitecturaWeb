package integrador3.integrador3.controllers;

import integrador3.integrador3.entities.Estudiante_Carrera;
import integrador3.integrador3.repository.Estudiante_CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("matriculas")
public class Estudiante_CarreraController {
    @Autowired
    private final Estudiante_CarreraRepository repository;

    public Estudiante_CarreraController(Estudiante_CarreraRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Iterable<Estudiante_Carrera> carreras() {
        return repository.findAll();
    }
}
