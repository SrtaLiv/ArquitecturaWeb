package com.microservicio_viaje;

import com.microservicio_viaje.util.CargaDeDatos;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
public class MicroservicioUserApplication {

	@Autowired
	CargaDeDatos cargaDeDatos;

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioUserApplication.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		if(cargaDeDatos.isTablaVacia()){
			cargaDeDatos.cargarDatosDesdeCSV();
		}
	}

}
