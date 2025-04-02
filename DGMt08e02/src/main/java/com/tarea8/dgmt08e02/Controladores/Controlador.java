package com.tarea8.dgmt08e02.Controladores;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tarea8.dgmt08e02.Domain.Cuenta;
import com.tarea8.dgmt08e02.Domain.Movimiento;
import com.tarea8.dgmt08e02.Servicios.Servicio;

import jakarta.validation.Valid;

@Controller
public class Controlador {

    @Autowired
    private Servicio servicio;
    private String errmsg = null;

    // Area cuentas
    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("cuentas", servicio.listAllCuentas());
        addError(model, errmsg);
        return "indexView";
    }

    @GetMapping("/signup")
    public String getSignUp(Model model) {
        Cuenta cuentaForm = new Cuenta();
        cuentaForm.setIBAN(servicio.generadorIBAN());
        model.addAttribute("cuentaForm", cuentaForm);
        addError(model, errmsg);
        return "signUpView";
    }

    @PostMapping("/signup/submit")
    public String postSignUp(@ModelAttribute("cuentaForm") @Valid Cuenta cuentaForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            errmsg = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return "redirect:/signup";
        }
        try {
            servicio.agregarCuenta(cuentaForm);
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/signup";
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{iban}")
    public String getEdit(@PathVariable String iban, Model model) {
        try {
            model.addAttribute("cuentaForm", servicio.getById(iban));
            addError(model, errmsg);
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/";
        }
        return "editView";
    }

    @PostMapping("/edit/submit")
    public String postEdit(@Valid Cuenta cuentaForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            errmsg = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return "redirect:/edit/" + cuentaForm.getIBAN();
        }
        try {
            servicio.modificarCuenta(cuentaForm);
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/edit/" + cuentaForm.getIBAN();
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{iban}")
    public String getDelete(@PathVariable String iban) {
        try {
            servicio.borrarCuenta(iban);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/";
    }

    // Area movimientos
    @GetMapping("/movements")
    public String getMovements(@RequestParam(required = false) String iban, Model model) {
        model.addAttribute("iban", iban);
        model.addAttribute("movimientos", servicio.listAllAcountMovements(iban));
        addError(model, errmsg);
        return "moveView";
    }

    @GetMapping("/movements/new")
    public String getNewMove(Model model) {
        Movimiento moveForm = new Movimiento();
        model.addAttribute("moveForm", moveForm);
        model.addAttribute("cuentas", servicio.listAllCuentas());
        addError(model, errmsg);
        return "newMoveView";
    }

    @PostMapping("/movements/new/submit")
    public String postNewMove(@Valid Movimiento moveForm, BindingResult bindingResult,
            Model model) {
        // Asigna la fecha y hora actual al hacer submit (Movido a servicio)
        // moveForm.setFechaHora(LocalDateTime.now());
        if (bindingResult.hasErrors()) {
            errmsg = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return getNewMove(model);
        }
        try {
            // A ver, procesemos esto
            // Thymeleaf no admite valores complejos como objetos en los formularios por lo
            // que tendría que envíar el id del objeto, antes da null si lo hago solo con el
            // objeto, así que paso la id en el valor y la uso para buscar el objeto con
            // getById, pero para obtener el string de cuenta tengo que utilizar el metodo
            // getIBAN del objeto... y solo funciona con ese metodo. Podría usar un DTO...

            moveForm.setCuenta(servicio.getById(moveForm.getCuenta().getIBAN()));
            servicio.agregarMovimiento(moveForm);
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/movements/new";
        }
        return "redirect:/movements";
    }

    @GetMapping("/accessError")
    public String getErrorView() {
        return "error/accessErrorView";
    }

    // Area utilidades (que mejoro poro a poco)
    private void addError(Model model, String error) {
        if (errmsg != null) {
            model.addAttribute("error", error);
            errmsg = null;
        }
    }

}
