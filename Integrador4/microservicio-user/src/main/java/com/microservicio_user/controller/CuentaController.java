package com.microservicio_user.controller;


import com.microservicio_user.entity.Cuenta;
import com.microservicio_user.services.CuentaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private CuentaService cs;

    @Autowired
    public CuentaController(CuentaService cs){
        this.cs=cs;
    }
    @Operation(summary = "Obtiene una cuenta")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cs.findById(id));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }
    @Operation(summary = "Obtiene todas las cuentas")
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cs.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @Operation(summary = "Agrega una cuenta")
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Cuenta entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cs.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Edita una cuenta")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Cuenta entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cs.update(id,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Elimina una cuenta")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cs.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }
    @Operation(summary = "Anula una cuenta")
    @PutMapping("/anular/{id}")
    public ResponseEntity<?> anularCuenta(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cs.anularCuenta(id));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar.\"}");

        }
    }
    @Operation(summary = "Habilida una cuenta")
    @PutMapping("/habilitar/{id}")
    public ResponseEntity<?> habilitarCuenta(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(cs.habilitarCuenta(id));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar.\"}");

        }
    }


}
