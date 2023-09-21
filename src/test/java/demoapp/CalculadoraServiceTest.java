package demoapp;

import demoapp.service.CalculadoraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CalculadoraServiceTest {

    @Autowired
    private CalculadoraService calculadoraService;

    @Test
    public void testOperarSuma() {
        assertThat(calculadoraService.operar(5, 3, '+')).isEqualTo(8);
    }

    @Test
    public void testOperarResta() {
        assertThat(calculadoraService.operar(5, 3, '-')).isEqualTo(2);
    }

    @Test
    public void testOperarMultiplicar() {
        assertThat(calculadoraService.operar(5, 3, '*')).isEqualTo(15);
    }

    @Test
    public void testOperarDividir() {
        assertThat(calculadoraService.operar(10, 2, '/')).isEqualTo(5);
    }

    @Test
    public void testOperarDividirPorCero() {
        assertThrows(ArithmeticException.class, () -> calculadoraService.operar(10, 0, '/'));
    }

    @Test
    public void testOperarOperacionInvalida() {
        assertThrows(IllegalArgumentException.class, () -> calculadoraService.operar(4, 0, 'a'));
    }
}
