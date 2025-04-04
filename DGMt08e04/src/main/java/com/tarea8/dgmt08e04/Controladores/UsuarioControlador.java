package com.tarea8.dgmt08e04.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tarea8.dgmt08e04.Servicios.UsuarioService;
import com.tarea8.dgmt08e04.domain.Roles;
import com.tarea8.dgmt08e04.domain.Usuario;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    UsuarioService servicio;
    private String errmsg = null;

    @GetMapping("/")
    public String getUserMain(Model model) {
        addError(model, errmsg);
        model.addAttribute("usuarios", servicio.listAllUsers());
        return "/usuario/IndexView";
    }

    @GetMapping("/new")
    public String getNewUser(Model model) {
        addError(model, errmsg);
        model.addAttribute("formulario", new Usuario());
        return "/usuario/newUserView";
    }

    @PostMapping("/new/submit")
    public String postNewUser(@Valid Usuario formulario, BindingResult bindingResult, Model model) {
        formulario.setRol(Roles.USER);
        if (bindingResult.hasErrors() || formulario.getContraseña().length() < 4) {
            model.addAttribute("formulario", formulario);
            model.addAttribute("error", "Hay errores en el formulario");
            return "/usuario/newUserView";
        }
        try {
            servicio.agregar(formulario);
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/usuario/new";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/usuario/";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/edit/{id}")
    public String getEditUser(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("formulario", servicio.getById(id));
            addError(model, errmsg);
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/usuario/";
        }
        return "/usuario/editUserView";
    }

    @PostMapping("/edit/submit")
    public String postEditUser(@Valid Usuario formulario, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || (formulario.getContraseña().length() < 4 && formulario.getContraseña().length() != 0)) {
            model.addAttribute("formulario", formulario);
            model.addAttribute("error", "Hay errores en el formulario");
            return "/usuario/editUserView";
        }
        try {
            servicio.modificar(formulario);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/usuario/";
    }

    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable Long id) {
        try {
            servicio.deleteUser(id);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/usuario/";
    }

    private void addError(Model model, String error) {
        if (errmsg != null) {
            model.addAttribute("error", error);
            errmsg = null;
        }
    }
}
