package com.microservicio_mantenimiento_admin.microservicio_mantenimiento_admin.service;

import com.microservicio_mantenimiento_admin.microservicio_mantenimiento_admin.model.Viaje;
import com.microservicio_mantenimiento_admin.microservicio_mantenimiento_admin.repository.IRepositoryViaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceViaje {
    @Autowired
    private IRepositoryViaje repositoryViaje;

    public List<Viaje> getAllViajes(){
        return repositoryViaje.findAll();
    }

    public Optional<Viaje> getViajeById(Long id){
        return  repositoryViaje.findById(id);
    }

    public Viaje saveViaje(Viaje viaje){
        Viaje result = repositoryViaje.save(viaje);
        return result;
    }

    public void updateViaje(Viaje viaje){
        repositoryViaje.save(viaje);
    }

    public void deleteViajeById(Long id){
        Optional<Viaje> viaje = getViajeById(id);
        if (!viaje.isEmpty()){
            repositoryViaje.deleteById(id);
        }
    }
}
