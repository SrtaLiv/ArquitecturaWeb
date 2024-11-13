package com.microservicio_viaje.service;

import com.microservicio_viaje.entity.Viaje;
import com.microservicio_viaje.entity.clases.Monopatin;
import com.microservicio_viaje.repository.RepositoryViaje;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceViaje {

    @Autowired
    RepositoryViaje repositoryViaje;

    @Autowired
    public ServiceViaje(RepositoryViaje repositoryViaje) {
        this.repositoryViaje = repositoryViaje;
    }

    @Transactional
    public List<Monopatin> findMonopatinesConMasDeXViajesPorAnio(int cantidad, int anio) {
        return repositoryViaje.findMonopatinesConMasDeXViajesPorAnio(cantidad, anio);
    }

    @Transactional
    public List<Viaje> findAll() throws Exception {
        return repositoryViaje.findAll();
    }
    @Transactional
    public Viaje findById(Long id) throws Exception {
        return repositoryViaje.findById(id).orElse(null);
    }

    @Transactional
    public Viaje save(Viaje entity) throws Exception {
        repositoryViaje.save(entity);
        return this.findById(entity.getId());
    }

    @Transactional
    public Viaje update(long id, Viaje updatedViaje) throws ChangeSetPersister.NotFoundException {
        Optional<Viaje> viajeOptional = repositoryViaje.findById(id);

        if (viajeOptional.isPresent()) {
            Viaje viaje = viajeOptional.get();
            viaje.setFecha(updatedViaje.getFecha());
            viaje.setKmRecorridos(updatedViaje.getKmRecorridos());
            viaje.setHoraFin(updatedViaje.getHoraFin());
            viaje.setHoraInicio(updatedViaje.getHoraInicio());
            viaje.setIdMonopatin(updatedViaje.getIdMonopatin());
            viaje.setIdUsuario(updatedViaje.getIdUsuario());
            viaje.setPausa(updatedViaje.isPausa());

            return repositoryViaje.save(viaje);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }


    @Transactional
    public ResponseEntity<?> delete(Long id) throws Exception {
        if (repositoryViaje.existsById(id)) {
            repositoryViaje.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El viaje con ID " + id + " no existe");
        }
    }
}
