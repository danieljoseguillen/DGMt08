package com.tarea8.dgmt08e04.Servicios;

import java.util.List;

import com.tarea8.dgmt08e04.domain.Usuario;
import com.tarea8.dgmt08e04.domain.UsuarioDTO;

public interface UsuarioService {

    List<Usuario> listAllUsers();

    Usuario getById(Long id);

    Usuario agregar(Usuario user);

    Usuario modificar(Usuario user);

    boolean deleteUser(Long id);

    UsuarioDTO convertToDTO(Usuario user);

    Usuario convertFromDTO(UsuarioDTO user);
    
}
