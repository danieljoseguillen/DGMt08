package com.tarea8.dgmt08e04.Servicios;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tarea8.dgmt08e04.domain.Usuario;
import com.tarea8.dgmt08e04.domain.UsuarioDTO;
import com.tarea8.dgmt08e04.repositorios.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repositorio;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ModelMapper modelMapper;

    public List<Usuario> listAllUsers() {
        return repositorio.findAll();
    };

    public Usuario getById(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
    }

    public Usuario agregar(Usuario user) {
        try {
            if (repositorio.findByNombre(user.getNombre()).orElse(null) != null) {
                throw new RuntimeException("El nombre ya existe.");
            }
            user.setContraseña(encoder.encode(user.getContraseña()));
            return repositorio.save(user);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el usuario. \n" + e.getMessage());
        }

    }

    public Usuario modificar(Usuario user) {
        try {
            Usuario base = repositorio.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
            if (encoder.matches(user.getContraseña(), base.getContraseña()) || user.getContraseña().isEmpty()) {
                user.setContraseña(base.getContraseña());
            } else {
                user.setContraseña(encoder.encode(user.getContraseña()));
            }
            return repositorio.save(user);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo modificar el usuario. \n" + e.getMessage());
        }
    }

    public boolean deleteUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usr = repositorio.findByNombre(authentication.getName())
                .orElseThrow(() -> new RuntimeException("No se pudo borrar el usuario"));
        if (usr.getId() == id) {
            throw new RuntimeException("No se puede borrar el usuario activo.");
        }
        repositorio.deleteById(id);
        if (repositorio.findById(id).orElse(null) != null) {
            throw new RuntimeException("No se pudo borrar el usuario");
        }
        return true;
    }

    public UsuarioDTO convertToDTO(Usuario user) {
        UsuarioDTO userB = modelMapper.map(user, UsuarioDTO.class);
        return userB;
    }

    public Usuario convertFromDTO(UsuarioDTO user) {
        Usuario userB = repositorio.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        Usuario userC = modelMapper.map(user, Usuario.class);
        if (user.getContraseña().isEmpty()) {
            userC.setContraseña(userB.getContraseña());
        }

        return userC;
    }
}
