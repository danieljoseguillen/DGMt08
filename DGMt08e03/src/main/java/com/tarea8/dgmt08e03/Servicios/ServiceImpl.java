package com.tarea8.dgmt08e03.Servicios;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tarea8.dgmt08e03.Domain.Cuenta;
import com.tarea8.dgmt08e03.Domain.Movimiento;
import com.tarea8.dgmt08e03.Repositorios.CuentaRepository;
import com.tarea8.dgmt08e03.Repositorios.MovimientoRepository;

@org.springframework.stereotype.Service
public class ServiceImpl implements Servicio {

    @Autowired
    private CuentaRepository cRepository;

    @Autowired
    private MovimientoRepository mRepository;

    public List<Cuenta> listAllCuentas() {
        return cRepository.findAll();
    }

    public Cuenta getById(String IBAN) {
        return cRepository.findById(IBAN).orElseThrow(() -> new RuntimeException("No se encontró la cuenta"));
    }

    public Cuenta agregarCuenta(Cuenta cuenta) {
        try {
            Cuenta c1 = cRepository.findById(cuenta.getIBAN()).orElse(null);
            if (c1 != null) {
                throw new RuntimeException("Ya existe una cuenta con ese IBAN");
            }
            cuenta.setSaldo(0d);
            return cRepository.save(cuenta);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar la cuenta.");
        }
    }

    public Cuenta modificarCuenta(Cuenta cuenta) {
        try {
            return cRepository.save(cuenta);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo modificar la cuenta.");
        }
    }

    public boolean borrarCuenta(String IBAN) {
        if (cRepository.findById(IBAN).isPresent()) {
            cRepository.deleteById(IBAN);
            return true;
        }
        throw new RuntimeException("No existe la cuenta.");
    }

    public List<Movimiento> listAllAcountMovements(String IBAN) {
        if (IBAN == null) {
            return mRepository.findAllByOrderByFechaHoraDesc();
        }
        return mRepository.findByCuentaIBANOrderByFechaHoraDesc(IBAN);
        // Cuenta cuenta = cRepository.findById(IBAN).orElseThrow(() -> new
        // RuntimeException("No se encontró la cuenta"));
        // return cuenta.getMovimientos();
    }

    public String generadorIBAN() {
        String ibn;
        Cuenta cn;
        do {
            ibn = "ES" + String.format("%020d", Math.abs(new Random().nextLong()));
            cn = cRepository.findById(ibn).orElse(null);
        } while (cn != null);
        return ibn;

    }

    public Movimiento agregarMovimiento(Movimiento mov) {
        try {
            // Verificar si el usuario puede retirar
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserRol = authentication.getAuthorities().toString();
            if (!currentUserRol.equals("[ROLE_ADMIN]") && !currentUserRol.equals("[ROLE_TITULAR]")) {
                if (mov.getSaldo() < 0) {
                    throw new RuntimeException("No tiene permisos suficientes para realizar retiros.");
                }
            }
            // Proceso para agregar la fecha y sustraer o agregar saldo tras guardar el
            // movimiento
            mov.setFechaHora(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            Cuenta cuenta = mov.getCuenta();
            System.out.println(cuenta.getSaldo() + mov.getSaldo());
            if (cuenta.getSaldo() + mov.getSaldo() < 0) {
                throw new RuntimeException("Saldo insuficiente en la cuenta");
            }
            cuenta.setSaldo(cuenta.getSaldo() + mov.getSaldo());
            Movimiento movem = mRepository.save(mov);
            modificarCuenta(cuenta);
            return movem;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
