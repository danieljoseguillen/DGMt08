package com.tarea8.dgmt08e03.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea8.dgmt08e03.Domain.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento,Long>{
    List<Movimiento> findAllByOrderByFechaHoraDesc();
    List<Movimiento> findByCuentaIBANOrderByFechaHoraDesc(String IBAN);
}
