package com.microservicio_user.controller;


import com.microservicio_user.entity.Pausa;
import com.microservicio_user.service.ServicePausa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/pausa")
public class ControllerPausa {
    private ServicePausa servicio;

    private RestTemplate restTemplate;

    @Autowired
    public ControllerPausa(ServicePausa servicio){
        this.servicio=servicio;
    }

    @Operation(summary = "Agrega una pausa")
    @PostMapping("/agregar")
    public ResponseEntity<?> agregarPausa(@RequestBody Pausa p){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(p));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }
    @Operation(summary = "Obtiene todas las pausas")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @Operation(summary = "Obtiene una pausa")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }
    @Operation(summary = "Edita una pausa")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Pausa entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, o no se encontró el ID. Revise los campos e intente nuevamente.\"}");
        }
    }
    @Operation(summary = "Elimina una pausa")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }

}
