package integrador3.integrador3.controllers;

import integrador3.integrador3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarreraController {
    @Qualifier("carreraRepository")
    @Autowired
    private CarreraRepository carreraRepository;

    public CarreraControllerJpa(@Qualifier("carreraRepository") CarreraRepository carreraRepository){
        this.carreraRepository = carreraRepository;
    }

    @GetMapping("/carrera")
    public Iterable<Carrera> carreras(){
        return carreraRepository.findAll();
    }

    @GetMapping("/carrera/{id}")
    public Optional<Carrera> carrera(@PathVariable Long id){
        return carreraRepository.findById(id);
    }

    @PostMapping("/carrera")
    public Carrera carrera(@RequestBody Carrera carrera){
        return carreraRepository.save(carrera);
    }

    @PutMapping("/carrera/{id}")
    Carrera replaceCarrera(@PathVariable Long id, @RequestBody Carrera carrera){
        return carreraRepository.save(carrera);
    }

    @DeleteMapping("/carrera/{id}")
    void deleteCarrera(@PathVariable Long id){
        carreraRepository.deleteById(id);
    }

    @GetMapping("/carrerafindCarreraConInscriptos")
    public Iterable<Carrera> findCarreraConInscriptos(){
        return carreraRepository.findCarreraConInscriptos();
    }
}
