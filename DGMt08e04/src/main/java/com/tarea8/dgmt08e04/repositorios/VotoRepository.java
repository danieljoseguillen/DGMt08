package com.tarea8.dgmt08e04.repositorios;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tarea8.dgmt08e04.domain.Voto;

public interface VotoRepository extends JpaRepository<Voto,Long>{

    @Query("select count(v) from Voto v group by v.pelicula order by v.pelicula") 
    ArrayList<Integer> CountByPelicula();

    Optional<Voto> findByUsuarioId(Long id);
}
