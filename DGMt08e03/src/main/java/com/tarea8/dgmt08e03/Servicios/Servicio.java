package com.tarea8.dgmt08e03.Servicios;

import java.util.List;

import com.tarea8.dgmt08e03.Domain.Cuenta;
import com.tarea8.dgmt08e03.Domain.Movimiento;

public interface Servicio {

    List<Cuenta> listAllCuentas();

    Cuenta getById(String IBAN);

    Cuenta agregarCuenta(Cuenta cuenta);

    Cuenta modificarCuenta(Cuenta cuenta);

    boolean borrarCuenta(String IBAN);

    List<Movimiento> listAllAcountMovements(String IBAN);

    Movimiento agregarMovimiento(Movimiento mov);

    String generadorIBAN();
}
