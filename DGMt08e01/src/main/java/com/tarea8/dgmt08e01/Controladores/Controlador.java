package com.tarea8.dgmt08e01.Controladores;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tarea8.dgmt08e01.Domain.Empleado;
import com.tarea8.dgmt08e01.Domain.sortDTO;
import com.tarea8.dgmt08e01.Servicios.eService;

import jakarta.validation.Valid;

@Controller
public class Controlador {

    @Autowired
    private eService servicio;
    private String errmsg = null;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("empleados", servicio.listAll());
        model.addAttribute("sortForm", new sortDTO());
        if (errmsg != null) {
            model.addAttribute("error", errmsg);
            errmsg = null;
        }
        return "indexView";
    }

    @PostMapping("/sort")
    public String postIndexName(sortDTO sortform, Model model) {
        model.addAttribute("empleados", servicio.listSorted(sortform.getSortgender(),sortform.getSortname()));
        model.addAttribute("sortForm", sortform);
        if (errmsg != null) {
            model.addAttribute("error", errmsg);
            errmsg = null;
        }
        return "indexView";
    }

    // @GetMapping("/sort/genero/{genero}")
    // public String getMethodName(@PathVariable Genero genero, Model model) {
    //     model.addAttribute("generoSeleccionado", genero);
    //     model.addAttribute("empleados", servicio.listGender(genero));
    //     model.addAttribute("sortForm", new sortDTO());
    //     if (errmsg != null) {
    //         model.addAttribute("error", errmsg);
    //         errmsg = null;
    //     }
    //     return "indexView";
    // }

    @GetMapping("/info/{id}")
    public String getInfo(@PathVariable long id, Model model) {
        try {
            model.addAttribute("empleado", servicio.getById(id));
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/";
        }
        return "infoView";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable long id, Model model) {
        try {
            model.addAttribute("empForm", servicio.getById(id));
            if (errmsg != null) {
                model.addAttribute("error", errmsg);
                errmsg = null;
            }
        } catch (Exception e) {
            errmsg = e.getMessage();
            return "redirect:/";
        }
        return "editView";
    }

    @PostMapping("/edit/submit")
    public String postedit(@Valid Empleado empform, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            errmsg = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | "));
            return "redirect:/edit/" + empform.getId();
        }
        try {
            servicio.modificar(empform);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        if (errmsg != null) {
            model.addAttribute("error", errmsg);
            errmsg = null;
        }
        model.addAttribute("empForm", new Empleado());
        return "addView";
    }

    @PostMapping("/add/submit")
    public String postAdd(@Valid Empleado empform, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(" | ")));
            model.addAttribute("empForm", empform);
            return "addView";
        }
        try {
            servicio.agregar(empform);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable long id) {
        try {
            servicio.borrarPorId(id);
        } catch (Exception e) {
            errmsg = e.getMessage();
        }
        return "redirect:/";
    }

    @GetMapping("/accessError")
    public String showAccessErrorPage() {
        return "error/accessErrorView";
    }
}
