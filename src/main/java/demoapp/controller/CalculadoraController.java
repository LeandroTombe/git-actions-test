package demoapp.controller;

import demoapp.service.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/calculadora")
public class CalculadoraController {

    @Autowired
    private CalculadoraService calculadoraService;

    @GetMapping("/formulario")
    public String showForm(Model model) {
        model.addAttribute("calculadoraForm", new CalculadoraForm());
        return "formulario";
    }

    @PostMapping("/calcular")
    public String calcular(@Valid CalculadoraForm calculadoraForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "formulario";
        }

        double resultado;

        try {
            char operadorChar = calculadoraForm.getOperador().charAt(0);
            resultado = calculadoraService.operar(calculadoraForm.getOperando1(),
                                                  calculadoraForm.getOperando2(),
                                                  operadorChar);
        } catch (ArithmeticException e) {
            model.addAttribute("error", "No se puede dividir por cero");
            return "resultado";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Operación no válida");
            return "resultado";
        }

        model.addAttribute("resultado", resultado);
        return "resultado";
    }
}
