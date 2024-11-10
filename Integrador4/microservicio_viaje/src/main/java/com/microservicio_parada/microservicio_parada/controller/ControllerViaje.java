package com.microservicio_parada.microservicio_parada.controller;

import com.microservicio_parada.microservicio_parada.model.Viaje;
import com.microservicio_parada.microservicio_parada.service.ServiceViaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ControllerViaje {
    @Autowired
    private ServiceViaje serviceViaje;

    @GetMapping("")
    public List<Viaje> getAllViajes(){
        return serviceViaje.getAllViajes();
    }

    @GetMapping("/viaje/{id}")
    public Optional<Viaje> getViajeById(@PathVariable Long id){
        return serviceViaje.getViajeById(id);
    }

    @PostMapping("/viaje")
    public ResponseEntity<Viaje> saveViaje(@RequestBody Viaje viaje){
        Viaje result = serviceViaje.saveViaje(viaje);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/viaje")
    public void updateViaje(@RequestBody Viaje viaje){
        serviceViaje.saveViaje(viaje);
    }

    @DeleteMapping("/viaje/{id}")
    public void deleteViajeById(@PathVariable Long id){
        Optional<Viaje> viaje = getViajeById(id);
        if (viaje.isPresent()){
            serviceViaje.deleteViajeById(id);
        }
    }
}
