package com.tarea8.dgmt08e04.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tarea8.dgmt08e04.domain.Pelicula;
import com.tarea8.dgmt08e04.domain.Usuario;
import com.tarea8.dgmt08e04.domain.Voto;
import com.tarea8.dgmt08e04.repositorios.PeliculaRepository;
import com.tarea8.dgmt08e04.repositorios.UsuarioRepository;
import com.tarea8.dgmt08e04.repositorios.VotoRepository;

@Service
public class servicios {
    @Autowired
    private UsuarioRepository uRepo;

    @Autowired
    private VotoRepository vRepo;

    @Autowired
    private PeliculaRepository pRepo;

    public List<Pelicula> getAllpeliculas() {
        return pRepo.findAll();
    }

    public boolean calcvoto(Long choice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(choice);
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Pelicula peli = pRepo.findById(choice).orElse(null);
            System.out.println(peli.getId());
            if (peli != null) {
                Usuario usr = uRepo.findByNombre(authentication.getName())
                        .orElseThrow(() -> new RuntimeException("No se encontró el usuario en la base de datos."));
                if (usr != null && vRepo.findByUsuarioId(usr.getId()).orElse(null) == null) {
                    vRepo.save(new Voto(null, peli, usr));
                    return true;
                }
            }
        }
        return false;
    }
}
