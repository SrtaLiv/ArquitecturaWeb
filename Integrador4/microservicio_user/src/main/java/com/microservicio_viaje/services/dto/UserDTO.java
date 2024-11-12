package com.microservicio_viaje.services.dto;

import com.microservicio_viaje.entity.Cuenta;
import com.microservicio_viaje.entity.User;
import lombok.Getter;

import java.util.Set;

@Getter
public class UserDTO {

    private long id;
    private String telefono;
    private String email;
    private String nombre;
    private String apellido;
    private String password;

    private Set<Cuenta> cuenta;

    public UserDTO() {}

    public UserDTO(User usuario) {
        this.id = usuario.getId();
        this.telefono = usuario.getTelefono();
        this.email = usuario.getEmail();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.cuenta = usuario.getCuentas();
        this.password=usuario.getPassword();
    }

    public UserDTO(long id, String telefono, String email, String nombre, String apellido,Set<Cuenta> cuenta, String password) {
        this.id = id;
        this.telefono = telefono;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuenta = cuenta;
        this.password=password;
    }
}

