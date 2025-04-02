package com.tarea8.dgmt08e02;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tarea8.dgmt08e02.Domain.Roles;
import com.tarea8.dgmt08e02.Domain.Usuario;
import com.tarea8.dgmt08e02.Servicios.UsuarioService;

@SpringBootApplication
public class Dgmt08e02Application {

	public static void main(String[] args) {
		SpringApplication.run(Dgmt08e02Application.class, args);
	}
		@Bean
	CommandLineRunner initData(UsuarioService servicio){
		return args -> {
			servicio.agregar(new Usuario(null,"admin", "1234", Roles.ADMIN));
		};
	}
}

