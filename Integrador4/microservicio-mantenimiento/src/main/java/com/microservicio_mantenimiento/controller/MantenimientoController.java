package com.microservicio_mantenimiento.controller;

import com.microservicio_mantenimiento.entity.mongo.Mantenimiento;
import com.microservicio_mantenimiento.service.MantenimientoServicio;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoController {

    private MantenimientoServicio ms;

    @Autowired
    public MantenimientoController(MantenimientoServicio ms) {
        this.ms = ms;
    }
    @Operation(summary = "Agrega un mantenimiento")
    @PostMapping("/agregar")
    public ResponseEntity<?> agregarMantenimiento(@RequestBody Mantenimiento m) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ms.save(m));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }
    @Operation(summary = "Edita un mantenimiento")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarMantenimiento(@PathVariable Long id, @RequestBody Mantenimiento m) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ms.update(id, m));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, o no se encontró el ID. Revise los campos e intente nuevamente.\"}");
        }
    }
    @Operation(summary = "Obtiene un monopatin en mantenimiento")
    @GetMapping("/getMonopatin/{idMonopatin}")
    public ResponseEntity<?> getMantenimientoPorIdMonopatin(@PathVariable Long idMonopatin) {
        try {
            Optional<Mantenimiento> mantenimiento = ms.findByIdMonopatin(idMonopatin);

            if (mantenimiento.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(mantenimiento.get());
            } else {
                String mensaje = "{\"mensaje\":\"No se pudo encontrar un monopatin en mantenimiento con el id ingresado\"}";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
            }

        } catch (Exception e) {
            String errorMessage = "{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
    @Operation(summary = "Obtiene todos los mantenimiento")
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ms.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @Operation(summary = "Obtiene por id mantenimiento")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(ms.findById(id));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }
    @Operation(summary = "Elimina un mantenimiento")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(ms.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }

}
