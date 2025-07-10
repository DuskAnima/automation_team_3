package cl.kibernum;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;


public class DiscountServiceTest 
{
     DiscountService discountService = new DiscountService();


    @Test
    public void shouldDiscount_15Percent() { // Prueba mayor a 100,000.1
        double inputAmount = ThreadLocalRandom.current().nextDouble(100000.1, 900000); // Número aleatorio
        double discountApplied = discountService.calcularDescuento(inputAmount); // Cálculo del servicio
        // Se compara el monto esperado (inputMonto * 0.85) con el resultado procesado por el servicio. 
        assertEquals((inputAmount * 0.85), discountApplied, 0.000001); // Margen de error de 6 decimales
    }
    
    @Test
    public void shouldDiscount_10Percent() { // Prueba entre 50k y 100k
        double inputAmount = ThreadLocalRandom.current().nextDouble(50000, 100000);
        double discountApplied = discountService.calcularDescuento(inputAmount);
        // Se compara el monto esperado (inputMonto * 0.9) con el resultado procesado por el servicio. 
        assertEquals((inputAmount * 0.9), discountApplied, 0.000001);
    }

    @Test
    public void noDiscountShouldApply() {
        double monto = ThreadLocalRandom.current().nextDouble(1, 49999.9);
        double montoConDescuento = discountService.calcularDescuento(monto);
        assertEquals(monto, montoConDescuento, 0); // Sin operaciones no hay necesidad de un márgen de error.
    }
}
