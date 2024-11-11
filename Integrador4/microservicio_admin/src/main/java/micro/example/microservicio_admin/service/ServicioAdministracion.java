package micro.example.microservicio_admin.service;


import micro.example.microservicio_admin.entity.Administrador;
import micro.example.microservicio_admin.repository.AdministracionRepo;
import micro.example.microservicio_admin.entity.clases.Mantenimiento;
import micro.example.microservicio_admin.entity.clases.Monopatin;
import micro.example.microservicio_admin.service.dto.AdministradorDTO;
import micro.example.microservicio_admin.service.dto.MonopatinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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
public class ServicioAdministracion {

    private RestTemplate restTemplate;
    private AdministracionRepo ar;

    @Autowired
    public ServicioAdministracion(AdministracionRepo ar, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.ar = ar;
    }

    @Transactional
    public ResponseEntity settearMonopatinAMantenimiento(Long id) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<MonopatinDTO> response = restTemplate.exchange("http://microservicio_monopatin/monopatines/" + id,
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<MonopatinDTO>(){});
        if (response.getStatusCode().is2xxSuccessful()) {
            MonopatinDTO monopatin = response.getBody();
            if(monopatin != null) {
                Monopatin m = new Monopatin(monopatin);
                if (!monopatin.isEnMantenimiento()) {
                    this.agregarMantenimiento(m);
                    m.setEnMantenimiento(true);
                    HttpEntity<Monopatin> requestEntity2 = new HttpEntity<>(m, headers);
                    ResponseEntity<MonopatinDTO> response2 = restTemplate.exchange("http://microservicio_monopatin/monopatines/editar/" + id,
                            HttpMethod.PUT, requestEntity2, new ParameterizedTypeReference<MonopatinDTO>() {
                            });
                    return response2;
                }else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El monopatin ya está en mantenimiento");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el monopatin a dar de baja");
    }
        @Transactional
        public ResponseEntity<?> agregarMantenimiento(Monopatin monopatin) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Mantenimiento mantenimiento = new Mantenimiento();
            mantenimiento.setId_monopatin(monopatin.getId());
            mantenimiento.setInicio(LocalDate.now());
            mantenimiento.setFin(null);
            mantenimiento.setKm_recorridos(monopatin.getKilometraje());

            HttpEntity<Mantenimiento> requestEntity = new HttpEntity<>(mantenimiento, headers);

            ResponseEntity<String> response = restTemplate.exchange("http://microservicio_mantenimiento/mantenimiento/agregar",
                    HttpMethod.POST, requestEntity, String.class);
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


