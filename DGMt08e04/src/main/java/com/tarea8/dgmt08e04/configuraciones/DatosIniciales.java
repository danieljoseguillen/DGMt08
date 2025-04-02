package com.tarea8.dgmt08e04.configuraciones;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tarea8.dgmt08e04.domain.Pelicula;
import com.tarea8.dgmt08e04.domain.Roles;
import com.tarea8.dgmt08e04.domain.Usuario;
import com.tarea8.dgmt08e04.repositorios.PeliculaRepository;
import com.tarea8.dgmt08e04.repositorios.UsuarioRepository;
import com.tarea8.dgmt08e04.repositorios.VotoRepository;

@Configuration
public class DatosIniciales {
        @Autowired
    private PasswordEncoder encoder;
    
    // @Bean
    // CommandLineRunner initData(UsuarioRepository uRepo, VotoRepository vRepo, PeliculaRepository pRepo) {
    //     return args -> {

    //         List<Pelicula> peliculas = List.of(
    //                 new Pelicula(null, "Sin novedad en el frente", "all_quiet_on_the_western_front.jpg",
    //                         new ArrayList<>()),
    //                 new Pelicula(null, "Hasta el ultimo hombre", "desmond_doss.jpg", new ArrayList<>()),
    //                 new Pelicula(null, "Midway (2019)", "midway2019.jpg", new ArrayList<>()),
    //                 new Pelicula(null, "Rescatando al soldado Ryan", "saving_private_ryan.jpg", new ArrayList<>()));
    //         pRepo.saveAll(peliculas);

    //         uRepo.save(new Usuario(null, "administrador", encoder.encode("administrador"), Roles.ADMIN));
    //     };
    // }
}
