package demoapp;


import demoapp.service.CalculadoraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
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
    public void shouldReturnCorrectResult() throws Exception {
        when(calculadoraService.operar(3, 4, '+')).thenReturn(7.0);

        this.mockMvc.perform(post("/calculadora/calcular")
                        .param("operando1", "3")
                        .param("operando2", "4")
                        .param("operador", "+"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Resultado: <span>7.0</span>")));
    }

    @Test
    public void shouldReturnDivideByZeroError() throws Exception {
        when(calculadoraService.operar(anyDouble(), eq(0.0), eq('/'))).thenThrow(new ArithmeticException("No se puede dividir por cero"));

        this.mockMvc.perform(post("/calculadora/calcular")
                        .param("operando1", "3")
                        .param("operando2", "0")
                        .param("operador", "/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("No se puede dividir por cero")));
    }

    @Test
    public void shouldReturnInvalidOperationError() throws Exception {
        when(calculadoraService.operar(anyDouble(), anyDouble(), eq('x'))).thenThrow(new IllegalArgumentException("Operación no válida"));

        this.mockMvc.perform(post("/calculadora/calcular")
                        .param("operando1", "3")
                        .param("operando2", "4")
                        .param("operador", "x"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Operador no válido")));
    }
}