package com.tarea8.dgmt08e02.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea8.dgmt08e02.Domain.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Long>{

    Optional<Usuario> findByNombre(String nombre);
}
