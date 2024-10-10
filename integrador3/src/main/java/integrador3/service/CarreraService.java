package integrador3.service;

import integrador3.DTO.CarreraDTO;
import integrador3.entities.Carrera;
import integrador3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("CarreraService")
public class CarreraService implements Servicio<Carrera>{

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

    public Object findCarreraConInscriptos() throws Exception {
        var resultado = carreraRepository.findCarreraConInscriptos();
        try{
            return resultado.stream().map(carrera->new CarreraDTO(new Carrera(),carrera.getCantidad())).collect(Collectors.toList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
