package com.microservicio_user;

import com.microservicio_user.entity.Cuenta;
import com.microservicio_user.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MicroservicioUserApplicationTests {

	private User user;

	@BeforeEach
	void setUp() {
		user = new User("Olivia", "123456789", "olivia@example.com", "Smith");
	}

	@Test
	void testConstructor() {
		assertEquals("Olivia", user.getNombre());
		assertEquals("123456789", user.getTelefono());
		assertEquals("olivia@example.com", user.getEmail());
		assertEquals("Smith", user.getApellido());
		assertNotNull(user.getFechaAlta());
		assertEquals(LocalDate.now(), user.getFechaAlta());
	}

}
