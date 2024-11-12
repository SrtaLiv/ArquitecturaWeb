package micro.example.microservicio_admin.controller;

import lombok.RequiredArgsConstructor;
import micro.example.microservicio_admin.entity.Administrador;
import micro.example.microservicio_admin.entity.clases.Parada;
import micro.example.microservicio_admin.service.ServicioAdministracion;
import micro.example.microservicio_admin.service.dto.MonopatinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/administrar")
@RequiredArgsConstructor
public class AdministracionController {
    @Autowired
    private ServicioAdministracion sa;

    @Autowired
    public AdministracionController(ServicioAdministracion sa) {
        this.sa = sa;
    }

    @DeleteMapping("/paradas/{id}")
    public ResponseEntity<?> eliminarParada(@PathVariable Long id) {
        try {
            return sa.deleteParada(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error. No se pudo eliminar la parada, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping("/paradas")
    public ResponseEntity<?> registrarParada(@RequestBody Parada entity) { //DTO?
        try {
            return sa.addParada(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error. No se pudo agregar la parada.\"}");
        }
    }

    @PostMapping("/monopatines")
    public ResponseEntity<?> agregarMonopatin(@RequestBody MonopatinDTO entity) {
        try {
            return sa.addMonopatin(entity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error. No se pudo agregar el monopatin, revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/monopatines/{id}")
    public ResponseEntity<?> eliminarMonopatin(@PathVariable Long id) {
        try {
            return sa.deleteMonopatin(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Error. No se pudo eliminar el monopatin, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/monopatines/setearAMantenimiento/{idMonopatin}")
    public ResponseEntity<ResponseEntity> setearAMantenimiento (@PathVariable Long idMonopatin){
        return ResponseEntity.status(HttpStatus.OK).body(sa.settearMonopatinAMantenimiento(idMonopatin));
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sa.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sa.findById(id));
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Administrador entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sa.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Administrador entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sa.update(id,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, o no se encontró el ID. Revise los campos e intente nuevamente.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(sa.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo eliminar intente nuevamente.\"}");
        }
    }

}
