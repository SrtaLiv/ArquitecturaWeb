package com.microservicio_viaje.services;

import com.microservicio_viaje.entity.User;
import com.microservicio_viaje.services.dto.UserDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.microservicio_viaje.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;


    @Transactional
    public List<UserDTO> findAll() throws Exception {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }
    @Transactional
    public UserDTO findById(Long id) throws Exception {
        return userRepository.findById(id).map(UserDTO::new).orElse(null);
    }
    @Transactional
    public UserDTO save(User entity) throws Exception {
        userRepository.save(entity);
        return this.findById(entity.getId());
    }
    @Transactional
    public User update(long id, User updatedUsuario) throws ChangeSetPersister.NotFoundException {
        User existingUsuario = userRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        // Actualizar los campos necesarios
        existingUsuario.setTelefono(updatedUsuario.getTelefono());
        existingUsuario.setEmail(updatedUsuario.getEmail());
        existingUsuario.setNombre(updatedUsuario.getNombre());
        existingUsuario.setApellido(updatedUsuario.getApellido());
        existingUsuario.setPassword(updatedUsuario.getPassword());

        // Guardar la entidad actualizada
        return userRepository.save(existingUsuario);
    }
    @Transactional
    public ResponseEntity<?> delete(Long id) throws Exception {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminación exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La entidad con ID " + id + " no existe");
        }
    }
}
