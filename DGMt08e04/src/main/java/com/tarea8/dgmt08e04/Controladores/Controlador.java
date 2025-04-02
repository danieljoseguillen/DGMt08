package com.tarea8.dgmt08e04.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarea8.dgmt08e04.Servicios.fileStorageService;
import com.tarea8.dgmt08e04.Servicios.formInfo;
import com.tarea8.dgmt08e04.Servicios.servicios;

import jakarta.validation.Valid;

@Controller
public class Controlador {
    @Autowired
    servicios servicios = new servicios();

    @Autowired
    public fileStorageService fileserv;

    private String msg = null;

    @GetMapping({ "/", "index", "/home" })
    public String showIndex(@RequestParam (required=false) String error,Model model) {
        model.addAttribute("formInfo", new formInfo());

        model.addAttribute("peliculas", servicios.getAllpeliculas());
        if (msg != null) {
            model.addAttribute("mensaje", msg);
            msg = null;
        }
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "index";
    }

    @PostMapping("/voto")
    public String votacion(@Valid formInfo forminfo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            msg = "Ha ocurrido un error.";
            return "redirect:/home";
        }
        // metodo en booleano para facilitar la verificación en controlador
        try {
            boolean res = servicios.calcvoto(forminfo.getVoto());
            if (res) {
                msg = "Voto realizado";
            } else {
                msg = "Ya ha realizado un voto.";
            }
        } catch (Exception e) {
            msg = e.getMessage();
        }

        return "redirect:/home";
    }

    @GetMapping("/accessError")
    public String getErrorView() {
        return "error/accessErrorView";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = fileserv.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }
}
