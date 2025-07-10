package cl.kibernum;


import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class DiscountServiceTest 
{
     DiscountService discountService = new DiscountService();

    ///////// Pruebas randomizadas \\\\\\\\\
    @Test
    public void shouldDiscount_15Percent_RamdomizedNumber() { // Prueba mayor a 100,000.1
        double inputAmount = ThreadLocalRandom.current().nextDouble(100000.1, 900000); // Número aleatorio
        double discountApplied = discountService.calcularDescuento(inputAmount); // Cálculo del servicio
        // Se compara el monto esperado (inputMonto * 0.85) con el resultado procesado por el servicio. 
        assertEquals((inputAmount * 0.85), discountApplied, 0.000001); // Margen de error de 6 decimales
        // No hay necesidad de redondear los números porque la lógica del servicio tampoco lo hace.
    }
    
    @Test
    public void shouldDiscount_10Percent_RamdomizedNumber() { // Prueba entre 50k y 100k
        double inputAmount = ThreadLocalRandom.current().nextDouble(50000, 100000);
        double discountApplied = discountService.calcularDescuento(inputAmount);
        // Se compara el monto esperado (inputMonto * 0.9) con el resultado procesado por el servicio. 
        assertEquals((inputAmount * 0.9), discountApplied, 0.000001);
    }

    @Test
    public void noDiscountShouldApply_RamdomizedNumber() {
        double inputAmount = ThreadLocalRandom.current().nextDouble(1, 49999.9);
        double discountApplied = discountService.calcularDescuento(inputAmount);
        assertEquals(inputAmount, discountApplied, 0); // Sin operaciones no hay necesidad de un márgen de error.
    }


    ///////// Pruebas parametrizadas \\\\\\\\\
    @ParameterizedTest
    @ValueSource(doubles = {})
    public void shouldDiscount_15Percent_EdgeCases() {

    }

    @Test 
    public void shouldDiscount_10Percent_EdgeCases() {

    }

    @Test 
    public void noDiscountShouldApply_EdgeCases() {

    }

    @Test
    public void shouldThrowError() {

    }
}
