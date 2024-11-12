package com.microservicio_parada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class MicroservicioParada {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioParada.class, args);
	}

}
