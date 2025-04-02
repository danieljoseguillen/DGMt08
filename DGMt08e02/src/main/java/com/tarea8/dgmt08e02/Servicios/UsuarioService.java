package com.tarea8.dgmt08e02.Servicios;

import java.util.List;

import com.tarea8.dgmt08e02.Domain.Usuario;

public interface UsuarioService {

    List<Usuario> listAllUsers();

    Usuario getById(Long id);

    Usuario agregar(Usuario user);

    Usuario modificar(Usuario user);

    boolean deleteUser(Long id);

}
