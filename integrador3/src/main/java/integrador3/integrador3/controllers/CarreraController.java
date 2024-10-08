package integrador3.integrador3.controllers;

import integrador3.integrador3.entities.Carrera;
import integrador3.integrador3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carrera")
public class CarreraController {
    @Autowired
    private final CarreraRepository repository;

    public CarreraController(CarreraRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public Iterable<Carrera> carreras() {
        return repository.findAll();
    }
}
