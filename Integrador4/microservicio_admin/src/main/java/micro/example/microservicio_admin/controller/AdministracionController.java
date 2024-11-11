package micro.example.microservicio_admin.controller;

import lombok.RequiredArgsConstructor;
import micro.example.microservicio_admin.entity.Administrador;
import micro.example.microservicio_admin.service.ServicioAdministracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/administrar")
@RequiredArgsConstructor
public class AdministracionController {
    private ServicioAdministracion sa;

    @Autowired
    public AdministracionController(ServicioAdministracion sa) {
        this.sa = sa;
    }

    @PutMapping("/monopatines/setearAMantenimiento/{idMonopatin}")
    public ResponseEntity<ResponseEntity> setearAMantenimiento (@PathVariable Long idMonopatin){it
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
