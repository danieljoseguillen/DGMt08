package com.tarea8.dgmt08e01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
// import org.springframework.boot.CommandLineRunner;
// import com.tarea7.dgmt07e01.Domain.Empleado;
// import com.tarea7.dgmt07e01.Modelos.Estado;
// import com.tarea7.dgmt07e01.Modelos.Genero;
// import com.tarea7.dgmt07e01.Servicios.eService;

@SpringBootApplication
public class Dgmt08e01Application {

	public static void main(String[] args) {
		SpringApplication.run(Dgmt08e01Application.class, args);
	}

	// @Bean
	// CommandLineRunner initData(eService servicio){
	// 	return args -> {
	// 		servicio.agregar(new Empleado(null,"Jose","Jose@gmail.com",30000d,Estado.ACTIVO,Genero.MASCULINO));
	// 		servicio.agregar(new Empleado(null,"Pedro","pedrog@gmail.com",32000d,Estado.ACTIVO,Genero.MASCULINO));
	// 		servicio.agregar(new Empleado(null,"Maria","mcarolina@gmail.com",35000d,Estado.INACTIVO,Genero.FEMENINO));
	// 	};
	// }
}
