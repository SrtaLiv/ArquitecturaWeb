package service;

import model.Parada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ParadaRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ParadaService {

    @Autowired
    private ParadaRepository paradaRepository;

    public List<Parada> getAllParadas() {
        return paradaRepository.findAll();
    }

    public Parada getParadaById(Long id) {
        Optional<Parada> parada = paradaRepository.findById(id);
        return parada.orElse(null);  // Devuelve null si no se encuentra
    }

    public Parada createParada(Parada parada) {
        return paradaRepository.save(parada);
    }

    public Parada updateParada(Long id, Parada paradaDetails) {
        Optional<Parada> paradaOptional = paradaRepository.findById(id);
        if (paradaOptional.isPresent()) {
            Parada parada = paradaOptional.get();
            parada.setLatitud(paradaDetails.getLatitud());
            parada.setLongitud(paradaDetails.getLongitud());
            parada.setKm_acumulados(paradaDetails.getKm_acumulados());
            // Aquí puedes añadir otros campos a actualizar si existen
            return paradaRepository.save(parada);
        } else {
            return null;
        }
    }

    public boolean deleteParada(Long id) {
        if (paradaRepository.existsById(id)) {
            paradaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
