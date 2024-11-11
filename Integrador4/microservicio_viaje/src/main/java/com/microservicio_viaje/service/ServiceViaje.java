package com.microservicio_viaje.service;

import com.microservicio_viaje.model.Viaje;
import com.microservicio_viaje.repository.IRepositoryViaje;
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
    IRepositoryViaje iRepositoryViaje;

    @Autowired
    public ServiceViaje(IRepositoryViaje iRepositoryViaje) {
        this.iRepositoryViaje = iRepositoryViaje;
    }

    @Transactional
    public List<Viaje> findAll() throws Exception {
        return iRepositoryViaje.findAll();
    }
    @Transactional
    public Viaje findById(Long id) throws Exception {
        return iRepositoryViaje.findById(id).orElse(null);
    }

    @Transactional
    public Viaje save(Viaje entity) throws Exception {
        iRepositoryViaje.save(entity);
        return this.findById(entity.getId());
    }

    @Transactional
    public Viaje update(long id, Viaje updatedViaje) throws ChangeSetPersister.NotFoundException {
        Optional<Viaje> viajeOptional = iRepositoryViaje.findById(id);

        if (viajeOptional.isPresent()) {
            Viaje viaje = viajeOptional.get();
            viaje.setFecha(updatedViaje.getFecha());
            viaje.setKmRecorridos(updatedViaje.getKmRecorridos());
            viaje.setHoraFin(updatedViaje.getHoraFin());
            viaje.setHoraInicio(updatedViaje.getHoraInicio());
            viaje.setIdMonopatin(updatedViaje.getIdMonopatin());
            viaje.setIdUsuario(updatedViaje.getIdUsuario());
            viaje.setPausa(updatedViaje.isPausa());

            return iRepositoryViaje.save(viaje);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }


    @Transactional
    public ResponseEntity<?> delete(Long id) throws Exception {
        if (iRepositoryViaje.existsById(id)) {
            iRepositoryViaje.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El viaje con ID " + id + " no existe");
        }
    }
}
