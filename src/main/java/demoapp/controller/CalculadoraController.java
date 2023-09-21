package demoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calculadora")
public class CalculadoraController {

    @GetMapping("/formulario")
    public String showForm(Model model) {
        model.addAttribute("calculadoraForm", new CalculadoraForm());
        return "formulario";
    }
}
