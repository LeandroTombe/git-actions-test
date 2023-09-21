package demoapp.service;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {

    public double operar(double num1, double num2, char operacion) throws ArithmeticException {
        switch (operacion) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("No se puede dividir por cero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Operación no válida");
        }
    }
}
