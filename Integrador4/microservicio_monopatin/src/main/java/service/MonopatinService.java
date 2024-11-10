package service;

import model.Monopatin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import repository.MonopatinRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MonopatinService {
    @Autowired
    private MonopatinRepository monopatinRepository;

    // Obtener todos los monopatines
    @GetMapping("/")
    public List<Monopatin> getMonopatin() {
        return monopatinRepository.findAll();
    }

    // Obtener un monopatín por ID
    @GetMapping("/{id}")
    public Monopatin getMonopatinById(Long id) {
        Optional<Monopatin> monopatin = monopatinRepository.findById(id);
        return monopatin.orElse(null);
    }

    // Crear un nuevo monopatín
    @PostMapping("/")
    public Monopatin createMonopatin(@RequestBody Monopatin monopatin) {
        return monopatinRepository.save(monopatin);
    }

    // Actualizar un monopatín existente
    /*
    @PutMapping("/{id}")
    public Monopatin updateMonopatin(@RequestBody Monopatin monopatinDetails) {
        return monopatinRepository.save(monopatinDetails);
    }*/
    @PutMapping("/{id}")
    public Monopatin updateMonopatin(Long id, Monopatin monopatinDetails) {
        Optional<Monopatin> monopatinOptional = monopatinRepository.findById(id);
        if (monopatinOptional.isPresent()) {
            Monopatin monopatin = monopatinOptional.get();
            monopatin.setLongitud(monopatinDetails.getLongitud());
            monopatin.setLatitud(monopatinDetails.getLatitud());
            return monopatinRepository.save(monopatin);
        } else {
            return null;
        }
    }

   /* @DeleteMapping("/{id}")
    public void deleteMonopatin(@RequestBody Monopatin monopatinDetails) {
        monopatinRepository.delete(monopatinDetails);
    }*/

    public boolean deleteMonopatin(Long id) {
        if (monopatinRepository.existsById(id)) {
            monopatinRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
