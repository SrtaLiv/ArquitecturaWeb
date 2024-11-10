package controller;

import model.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MonopatinService;

import java.util.List;

@RestController
@RequestMapping("/monopatines")
public class MonopatinController {

    @Autowired
    private MonopatinService monopatinService;

    // Obtener todos los monopatines
    @GetMapping("/")
    public ResponseEntity<List<Monopatin>> getMonopatin() {
        List<Monopatin> monopatines = monopatinService.getMonopatin();
        return ResponseEntity.ok(monopatines);
    }

    // Obtener un monopatín por ID
    @GetMapping("/{id}")
    public ResponseEntity<Monopatin> getMonopatinById(@PathVariable Long id) {
        Monopatin monopatin = monopatinService.getMonopatinById(id);
        if (monopatin != null) {
            return ResponseEntity.ok(monopatin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo monopatín
    @PostMapping("/")
    public ResponseEntity<Monopatin> createMonopatin(@RequestBody Monopatin monopatin) {
        Monopatin newMonopatin = monopatinService.createMonopatin(monopatin);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMonopatin);
    }

    // Actualizar un monopatín existente
    @PutMapping("/{id}")
    public ResponseEntity<Monopatin> updateMonopatin(@PathVariable Long id, @RequestBody Monopatin monopatinDetails) {
        Monopatin updatedMonopatin = monopatinService.updateMonopatin(id, monopatinDetails);
        if (updatedMonopatin != null) {
            return ResponseEntity.ok(updatedMonopatin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un monopatín por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonopatin(@PathVariable Long id) {
        if (monopatinService.deleteMonopatin(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
