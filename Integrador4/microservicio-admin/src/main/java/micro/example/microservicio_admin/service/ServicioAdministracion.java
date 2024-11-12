package micro.example.microservicio_admin.service;

import lombok.RequiredArgsConstructor;
import micro.example.microservicio_admin.entity.Administrador;
import micro.example.microservicio_admin.entity.clases.Monopatin;
import micro.example.microservicio_admin.feignClients.MantenimientoFeignClient;
import micro.example.microservicio_admin.feignClients.MonopatinFeignClient;
import micro.example.microservicio_admin.repository.AdministracionRepo;
import micro.example.microservicio_admin.entity.clases.Mantenimiento;
import micro.example.microservicio_admin.service.dto.AdministradorDTO;
import micro.example.microservicio_admin.service.dto.MonopatinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicioAdministracion {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MonopatinFeignClient monopatinFeignClient;

    @Autowired
    private AdministracionRepo ar;

    @Autowired
    MantenimientoFeignClient mantenimientoFeignClient;

    @Autowired
    public ServicioAdministracion(AdministracionRepo ar, RestTemplate restTemplate,
                                  MonopatinFeignClient monopatinFeignClient, MantenimientoFeignClient mantenimientoFeignClient) {
        this.restTemplate = restTemplate;
        this.ar = ar;
        this.monopatinFeignClient = monopatinFeignClient;
        this.mantenimientoFeignClient = mantenimientoFeignClient;
    }

    @Transactional
    public ResponseEntity addMonopatin(MonopatinDTO monopatin){
        ResponseEntity<MonopatinDTO> response = monopatinFeignClient.saveMonopatin(monopatin);
        return ResponseEntity.ok(response.getBody());
    }

   /* @Transactional
    public ResponseEntity<String> deleteMonopatin(Long idMonopatin) {
        try {
            ResponseEntity<Void> response = monopatinFeignClient.deleteMonopatin(idMonopatin);

            if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Monopatín eliminado exitosamente.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error al eliminar el monopatín. Verifique el ID.");
            }
        } catch (Exception e) {
            // Log de la excepción para fines de depuración
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error del servidor al intentar eliminar el monopatín.");
        }
    }
*/
    @Transactional
    public ResponseEntity settearMonopatinAMantenimiento(Long id) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        Monopatin monopatin = monopatinFeignClient.getMonopatinById(id);
        if(monopatin != null) {
            if (!monopatin.isEnMantenimiento()) {
                this.agregarMantenimiento(monopatin);
                monopatin.setEnMantenimiento(true);
                ResponseEntity<Monopatin> response2 = monopatinFeignClient.updateMonopatin(id,monopatin);
                return response2;
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El monopatin ya está en mantenimiento");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el monopatin a dar de baja");
    }

    @Transactional
    public ResponseEntity<?> agregarMantenimiento(Monopatin monopatin) {

        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setId_monopatin(monopatin.getId());
        mantenimiento.setInicio(LocalDate.now());
        mantenimiento.setFin(null);
        mantenimiento.setKm_recorridos(monopatin.getKilometraje());

        ResponseEntity<Mantenimiento> response = mantenimientoFeignClient.agregarMantenimiento(mantenimiento);
        return response;
    }
    @Transactional
    public List<AdministradorDTO> findAll() throws Exception {
        return ar.findAll().stream().map(AdministradorDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public AdministradorDTO findById(Long id) throws Exception {
        return ar.findById(id).map(AdministradorDTO::new).orElse(null);
    }

    @Transactional
    public AdministradorDTO save(Administrador entity) throws Exception {
        ar.save(entity);
        return this.findById(entity.getId().longValue());
    }

    @Transactional
    public Administrador update(Long id, Administrador updatedAdministrador) throws Exception {
        Optional<Administrador> optionalAdministrador = ar.findById(id);

        if (optionalAdministrador.isPresent()) {
            Administrador existingAdministrador = optionalAdministrador.get();

            existingAdministrador.setNombre(updatedAdministrador.getNombre());

            ar.save(existingAdministrador);

            return existingAdministrador;
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Transactional
    public ResponseEntity<String> delete(Long id) throws Exception {
        if (ar.existsById(id)) {
            ar.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La entidad con ID " + id + " no existe");
        }
    }
}


