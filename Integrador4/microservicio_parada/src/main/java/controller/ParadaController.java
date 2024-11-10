package controller;

import model.Parada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ParadaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paradas")
public class ParadaController {

    @Autowired
    private ParadaService paradaService;

    @GetMapping("/")
    public ResponseEntity<List<Parada>> getAllParadas() {
        List<Parada> paradas = paradaService.getAllParadas();
        return new ResponseEntity<>(paradas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parada> getParadaById(@PathVariable Long id) {
        Optional<Parada> parada = Optional.ofNullable(paradaService.getParadaById(id));
        return parada.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Parada> createParada(@RequestBody Parada parada) {
        Parada newParada = paradaService.createParada(parada);
        return new ResponseEntity<>(newParada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parada> updateParada(@PathVariable Long id, @RequestBody Parada paradaDetails) {
        Optional<Parada> updatedParada = Optional.ofNullable(paradaService.updateParada(id, paradaDetails));
        return updatedParada.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParada(@PathVariable Long id) {
        boolean deleted = paradaService.deleteParada(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
