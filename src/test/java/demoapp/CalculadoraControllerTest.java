package demoapp;

import demoapp.service.CalculadoraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculadoraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculadoraService calculadoraService;

    @Test
    public void testValidOperandsAndOperator() throws Exception {
        // Mock the CalculadoraService
        when(calculadoraService.operar(1.0, 2.0, '+')).thenReturn(3.0);

        this.mockMvc.perform(post("/calculadora/calcular")
                        .param("operando1", "1.0")
                        .param("operando2", "2.0")
                        .param("operador", "+"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Resultado: <span>3.0</span>")));
    }

    @Test
    public void testDivisionByZero() throws Exception {
        // Mock the CalculadoraService to throw ArithmeticException for division by zero
        when(calculadoraService.operar(1.0, 0.0, '/')).thenThrow(new ArithmeticException());

        this.mockMvc.perform(post("/calculadora/calcular")
                        .param("operando1", "1.0")
                        .param("operando2", "0.0")
                        .param("operador", "/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("No se puede dividir por cero")));
    }
}
