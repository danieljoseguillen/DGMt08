package com.tarea8.dgmt08e01.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea8.dgmt08e01.Domain.Empleado;
import com.tarea8.dgmt08e01.Modelos.Genero;
import com.tarea8.dgmt08e01.Repositorios.EmpleadoRepository;

@Service
public class eServiceImpl implements eService {

    @Autowired
    EmpleadoRepository repositorio;

    public List<Empleado> listAll() {
        return repositorio.findAll();
    }

    public List<Empleado> listSorted(Genero genero,String val) {
        if (genero==null) {
            return repositorio.findByNombreContainingIgnoreCase(val);
        }else if(val.isEmpty() || val==null){
            return repositorio.findByGenero(genero);
        }else{
            return repositorio.findByGeneroAndNombreContainingIgnoreCase(genero, val);
        }
    }

    public List<Empleado> listGender(Genero genero) {
        return repositorio.findByGenero(genero);
    }

    public Empleado getById(long id) {
        return repositorio.findById(id).orElseThrow(()->new RuntimeException("No se encontraron empleados con la ID indicada."));
    }

    public boolean agregar(Empleado empleado) {
        try {
            repositorio.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el empleado.");
        }
        return true;
    }

    public boolean modificar(Empleado empleado) {
        try {
            repositorio.save(empleado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo modificar el empleado.");
        }
        return true;
    }

    public boolean borrarPorId(long id) {

            if (repositorio.findById(id).isPresent()) {
                repositorio.deleteById(id);
                return true;
            }
        
        throw new RuntimeException("No existe el empleado.");
    }

}
