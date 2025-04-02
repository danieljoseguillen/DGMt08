package com.tarea8.dgmt08e04.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea8.dgmt08e04.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByNombre(String nombre);
}
