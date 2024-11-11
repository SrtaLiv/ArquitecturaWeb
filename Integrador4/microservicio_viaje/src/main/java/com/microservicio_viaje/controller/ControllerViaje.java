package com.microservicio_viaje.controller;

import com.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.service.ServiceViaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/viajes")
@RestController
public class ControllerViaje {

    private ServiceViaje serviceViaje;

    @Autowired
    public ControllerViaje(ServiceViaje serviceViaje) {
        this.serviceViaje = serviceViaje;
    }

    @GetMapping("")
    public ResponseEntity<List<Viaje>> getAllViajes() throws Exception {
        try{
            List<Viaje> viaje = serviceViaje.findAll();
            return new ResponseEntity<>(viaje, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> getViajeById(@PathVariable Long id) throws Exception {
        Optional<Viaje> viaje = Optional.ofNullable(serviceViaje.findById(id));
        return viaje.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<?> createViaje(@RequestBody Viaje entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(serviceViaje.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaje> updateViaje(@PathVariable Long id, @RequestBody Viaje viaje) throws ChangeSetPersister.NotFoundException {
        Optional<Viaje> updateViaje = Optional.ofNullable(serviceViaje.update(id, viaje));
        return updateViaje.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteViaje(@PathVariable Long id) throws Exception {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(serviceViaje.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }
}
