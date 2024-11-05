package com.microservicio_user.util;

import com.microservicio_user.entity.Cuenta;
import com.microservicio_user.entity.User;
import com.microservicio_user.repository.UserRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.format.DateTimeFormatter;

@Component
public class CargaDeDatos {

    UserRepository ur;


    @Autowired
    public CargaDeDatos( UserRepository ur) {
        this.ur = ur;
    }

    public void cargarDatosDesdeCSV() throws IOException {
        // Utiliza InputStream para cargar el archivo desde el classpath
        InputStream inputStream = getClass().getResourceAsStream("/usuarios.csv");
        if (inputStream == null) {
            throw new IOException("Archivo usuarios.csv no encontrado en resources.");
        }

        // Lee el archivo CSV
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             CSVParser datosUsuarios = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord usuario : datosUsuarios) {
                User u = new User();

                u.setId(Long.parseLong(usuario.get("idUsuario")));
                u.setTelefono(usuario.get("telefono"));
                u.setApellido(usuario.get("apellido"));
                u.setNombre(usuario.get("nombre"));
                u.setEmail(usuario.get("email"));
                u.setPassword(usuario.get("password"));
                ur.save(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
