package com.microservicio_viaje.service;


import com.microservicio_viaje.entity.Precio;
import com.microservicio_viaje.repository.RepositoryPrecio;
import com.microservicio_viaje.dto.PrecioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioPrecio {
    private RepositoryPrecio pr;

    @Autowired
    public ServicioPrecio(RepositoryPrecio pr) {
        this.pr = pr;
    }

    @Transactional
    public List<PrecioDTO> findAll() throws Exception {
        return pr.findAll().stream().map(PrecioDTO::new).collect(Collectors.toList());
    }
    @Transactional
    public PrecioDTO findById(Long id) throws Exception {
        return pr.findById(id).map(PrecioDTO::new).orElse(null);
    }
    @Transactional
    public PrecioDTO save(Precio p) throws Exception {
        pr.save(p);
        return this.findById(p.getId());
    }

    @Transactional
    public Precio update(Long id, Precio updatedPrecio) throws ChangeSetPersister.NotFoundException {
        Precio existingPrecio = pr.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        // Actualizar los campos necesarios
        existingPrecio.setClave(updatedPrecio.getClave());
        existingPrecio.setValor(updatedPrecio.getValor());
        existingPrecio.setFechaFacturacion(updatedPrecio.getFechaFacturacion());
        existingPrecio.setValorPorPausaExtendida(updatedPrecio.getValorPorPausaExtendida());

        // Guardar la entidad actualizada
        return pr.save(existingPrecio);
    }
    @Transactional
    public ResponseEntity<String> delete(Long id) throws Exception {
        if (pr.existsById(id)) {
            pr.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La entidad con ID " + id + " no existe");
        }
    }
}