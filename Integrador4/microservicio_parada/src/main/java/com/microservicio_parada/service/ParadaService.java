package com.microservicio_parada.service;

import jakarta.transaction.Transactional;
import com.microservicio_parada.model.Parada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.microservicio_parada.repository.ParadaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParadaService {

    @Autowired
    ParadaRepository paradaRepository;

    @Autowired
    public ParadaService(ParadaRepository paradaRepository) {
        this.paradaRepository = paradaRepository;
    }

    @Transactional
    public List<Parada> findAll() throws Exception {
        return paradaRepository.findAll();
    }
    @Transactional
    public Parada findById(Long id) throws Exception {
        return paradaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Parada save(Parada entity) throws Exception {
        paradaRepository.save(entity);
        return this.findById(entity.getId());
    }

    @Transactional
    public Parada update(long id, Parada updatedUsuario) throws ChangeSetPersister.NotFoundException {
        Optional<Parada> paradaOptional = paradaRepository.findById(id);

        if (paradaOptional.isPresent()) {
            Parada parada = paradaOptional.get();
            parada.setLatitud(parada.getLatitud());
            parada.setLongitud(parada.getLongitud());
            parada.setKm_acumulados(parada.getKm_acumulados());
            return paradaRepository.save(parada);
        } else {
            return null;
        }
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) throws Exception {
        if (paradaRepository.existsById(id)) {
            paradaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La parada con ID " + id + " no existe");
        }
    }
}


