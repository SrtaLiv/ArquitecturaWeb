package integrador3.controllers;

import integrador3.service.Estudiante_CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("matriculas")
public class Estudiante_CarreraController {
    @Autowired
    private final Estudiante_CarreraService estudiante_CarreraService;

    public Estudiante_CarreraController(Estudiante_CarreraService estudianteCarreraService) {
        estudiante_CarreraService = estudianteCarreraService;
    }


}
