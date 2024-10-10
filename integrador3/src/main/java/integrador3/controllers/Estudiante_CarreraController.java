package integrador3.controllers;

import integrador3.entities.Carrera;
import integrador3.entities.Estudiante;
import integrador3.entities.Estudiante_Carrera;
import integrador3.jsonEntities.Estudiante_CarreraJSON;
import integrador3.service.CarreraService;
import integrador3.service.EstudianteService;
import integrador3.service.Estudiante_CarreraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("matriculas")
public class Estudiante_CarreraController {
    private static final Logger log = LoggerFactory.getLogger(Estudiante_CarreraController.class);
    @Autowired
    private final Estudiante_CarreraService estudiante_CarreraService;
    @Autowired
    private final EstudianteService estudianteService;
    @Autowired
    private final CarreraService carreraService;

    public Estudiante_CarreraController(Estudiante_CarreraService estudianteCarreraService, EstudianteService estudianteService, CarreraService carreraService) {
        estudiante_CarreraService = estudianteCarreraService;
        this.estudianteService = estudianteService;
        this.carreraService = carreraService;
    }
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante_CarreraJSON entity) throws Exception {
        Estudiante est = estudianteService.findById(entity.getId_estudiante());
        Carrera car = carreraService.findById(entity.getId_carrera());
        Estudiante_Carrera ec = new Estudiante_Carrera(entity.getId(), est, car, entity.getAnio_inicio(), entity.getAnio_fin(), entity.getAntiguedad());
        log.warn("entity" + entity);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudiante_CarreraService.save(ec));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo crear el estudiante, revise los campos e intente nuevamente.\"}");
        }
    }
    @GetMapping("/list")
    public ResponseEntity<?> getAll(){
        log.warn("Iniciando el servicio de estudiante carrera");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudiante_CarreraService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }


}
