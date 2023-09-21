package demoapp.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CalculadoraForm {
    @NotNull(message = "El operando 1 no puede ser nulo")
    private Double operando1;

    @NotNull(message = "El operando 2 no puede ser nulo")
    private Double operando2;

    @NotNull(message = "El operador no puede ser nulo")
    @Pattern(regexp = "[+\\-*/]", message = "Operador no válido")
    private String operador;

    // Getters y setters

    public Double getOperando1() {
        return operando1;
    }

    public void setOperando1(Double operando1) {
        this.operando1 = operando1;
    }

    public Double getOperando2() {
        return operando2;
    }

    public void setOperando2(Double operando2) {
        this.operando2 = operando2;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }
}

