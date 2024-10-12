package integrador3.service;

import integrador3.DTO.CarreraDTO;
import integrador3.entities.Carrera;
import integrador3.repository.CarreraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("CarreraService")
public class CarreraService implements Servicio<Carrera>{

    private static final Logger log = LoggerFactory.getLogger(CarreraService.class);
    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<Carrera> findAll() throws Exception {
        return carreraRepository.findAll();
    }

    public Carrera findById(int id) throws Exception {
        try{
            Optional<Carrera> carrera = carreraRepository.findById(id);
            return carrera.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Carrera save(Carrera entity) throws Exception {
        try {
            return carreraRepository.save(entity);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Carrera update(int id, Carrera entity) throws Exception {
        try{
            Optional<Carrera> entityOpcional = carreraRepository.findById(id);
            Carrera carrera = entityOpcional.get();
            carrera = carreraRepository.save(entity);
            return carrera;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        try {
            if (carreraRepository.existsById(id)){
                carreraRepository.deleteById(id);
                return true;
            }
            else {
                throw new Exception();
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<CarreraDTO> findCarreraConInscriptos() throws Exception {
        try{
            log.info("llegue");
            List<Object[]> query = carreraRepository.findCarreraConInscriptos();
            log.info("llegue x2 ");
            List<CarreraDTO> carreras = new ArrayList<>();
            for (Object[] elemento : query){
                Carrera c = carreraRepository.findById((Integer) elemento[0]).get();
                Integer cantidad = Math.toIntExact((Long) elemento[1]);
                CarreraDTO carrera = new CarreraDTO(c, cantidad);
                carreras.add(carrera);
            }
            return carreras;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
