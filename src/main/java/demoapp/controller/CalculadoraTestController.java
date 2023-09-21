package demoapp.controller;

import demoapp.service.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
@Controller
@RequestMapping("/calculadora-test")
public class CalculadoraTestController {

    @Autowired
    private CalculadoraService calculadoraService;

    @GetMapping("/probar")
    public @ResponseBody String probarOperaciones() {
        StringBuilder sb = new StringBuilder();

        double suma = calculadoraService.operar(5, 3, '+');
        sb.append("Resultado de suma: ").append(suma).append("\n");

        double resta = calculadoraService.operar(5, 3, '-');
        sb.append("Resultado de resta: ").append(resta).append("\n");

        double multiplicacion = calculadoraService.operar(5, 3, '*');
        sb.append("Resultado de multiplicación: ").append(multiplicacion).append("\n");

        double division = calculadoraService.operar(10, 2, '/');
        sb.append("Resultado de división: ").append(division).append("\n");

        try {
            calculadoraService.operar(10, 0, '/');
        } catch (ArithmeticException e) {
            sb.append("Excepción capturada en la división por cero: ").append(e.getMessage());
        }

        return sb.toString();
    }
}

