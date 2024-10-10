package integrador3.controllers;

import integrador3.entities.Carrera;
import integrador3.entities.Estudiante;
import integrador3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("estudiantes")
public class EstudianteController {
    @Autowired
    private final EstudianteRepository estudianteRepository;

    public EstudianteController(EstudianteRepository repository) {
        this.estudianteRepository = repository;
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteRepository.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo crear el estudiante, revise los campos e intente nuevamente.\"}");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteRepository.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo traer los estudiantes, revise los campos e intente nuevamente.\"}");
        }

    }
}
