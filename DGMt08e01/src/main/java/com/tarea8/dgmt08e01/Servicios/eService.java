package com.tarea8.dgmt08e01.Servicios;

import java.util.List;

import com.tarea8.dgmt08e01.Domain.Empleado;
import com.tarea8.dgmt08e01.Modelos.Genero;

public interface eService {

    List<Empleado>listAll();
    List<Empleado>listSorted(Genero genero,String val);
    Empleado getById(long id);
    boolean agregar(Empleado empleado);
    boolean modificar(Empleado empleado);
    boolean borrarPorId(long id);
}
