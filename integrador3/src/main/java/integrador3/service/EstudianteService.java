package integrador3.service;

import integrador3.DTO.CarreraDTO;
import integrador3.DTO.EstudianteDTO;
import integrador3.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("EstudianteService")
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;


}
