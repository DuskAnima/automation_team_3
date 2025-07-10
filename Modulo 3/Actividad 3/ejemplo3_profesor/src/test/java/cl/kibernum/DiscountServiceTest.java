package cl.kibernum;


import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class DiscountServiceTest 
{
     DiscountService discountService = new DiscountService();

    ///////// Pruebas randomizadas \\\\\\\\\
    @Test
    public void shouldDiscount_15Percent() { // Prueba mayor a 100,000.1
        double inputAmount = ThreadLocalRandom.current().nextDouble(100000.1, 900000); // Número aleatorio
        double discountApplied = discountService.calcularDescuento(inputAmount); // Cálculo del servicio
        // Se compara el monto esperado (inputMonto * 0.85) con el resultado procesado por el servicio. 
        assertEquals((inputAmount * 0.85), discountApplied); // No hay necesidad de redondear los números ni agregar delta porque la lógica del servicio tampoco lo hace.
        // Verificación del caso borde
        double edgeInputAmount = Math.nextUp(100000.0); // Numero flotante siguiente.
        assertThat(discountService.calcularDescuento(edgeInputAmount)).isEqualTo(edgeInputAmount * 0.85);
    }
    
    @Test
    public void shouldDiscount_10Percent() { // Prueba entre 50k y 100k
        double inputAmount = ThreadLocalRandom.current().nextDouble(50000, 100000);
        double discountApplied = discountService.calcularDescuento(inputAmount);
        // Se compara el monto esperado (inputMonto * 0.9) con el resultado procesado por el servicio. 
        assertEquals((inputAmount * 0.9), discountApplied);
        // Verificación del caso borde
        double maxEdgeInputAmount = 100000.0; // Numero máximo
        double minEdgeInputAmount = 50000.0; // Numero mínimo
        assertThat(discountService.calcularDescuento(maxEdgeInputAmount)).isEqualTo(maxEdgeInputAmount * 0.9);
        assertThat(discountService.calcularDescuento(minEdgeInputAmount)).isEqualTo(minEdgeInputAmount * 0.9);
    }

    @Test
    public void noDiscountShouldApply() { // Prueba menos de 50k
        double inputAmount = ThreadLocalRandom.current().nextDouble(1, 49999.9);
        double discountApplied = discountService.calcularDescuento(inputAmount);
        assertEquals(inputAmount, discountApplied); // Sin operaciones no hay necesidad de un márgen de error.
        // Verificación del caso borde.
        double edgeInputAmount = Math.nextDown(50000.0); // Numero flotante anterior.
        assertThat(discountService.calcularDescuento(edgeInputAmount)).isEqualTo(edgeInputAmount);
    }

    @ParameterizedTest // Test Recibe múltiples parámetros e itera con ellos.
    @ValueSource(doubles = {0.0, -1.0})
    public void shouldThrowError(double badInputs) { // Prueba errores.
        assertThatThrownBy(() -> discountService.calcularDescuento(badInputs))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
