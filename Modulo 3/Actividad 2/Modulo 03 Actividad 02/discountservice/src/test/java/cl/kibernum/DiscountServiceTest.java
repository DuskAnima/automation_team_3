package cl.kibernum;

import static org.junit.Assert.assertEquals;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;


public class DiscountServiceTest 
{
     DiscountService discountService = new DiscountService();


    @Test
    public void testDescuento15Porciento() { // Prueba mayor a 100,000.1
        // Monto aleatorio dentro de los parámetros
        double monto = ThreadLocalRandom.current().nextDouble(100000.1, 900000); 
        double montoEsperado = monto * 0.85; // Se declara monto esperado
        double montoConDescuento = discountService.calcularDescuento(monto); // Cálculo del servicio
        assertEquals(montoEsperado, montoConDescuento, 0.000001); // Margen de error de 6 decimales
    }
    
    @Test
    public void testDescuento10Porciento() { // Prueba entre 50k y 100k
        double monto = ThreadLocalRandom.current().nextDouble(50000, 100000);
        monto = Math.round(monto * 10.0) / 10.0;
        double montoConDescuento = discountService.calcularDescuento(monto);
        montoConDescuento = Math.round(montoConDescuento * 10.0) / 10.0;
        assertEquals((monto * 0.9), montoConDescuento, 100000);
    }

    @Test
    public void testSinDescuento() {
        double monto = ThreadLocalRandom.current().nextDouble(1, 49999.9);
        monto = Math.round(monto * 10.0) / 10.0;
        double montoConDescuento = discountService.calcularDescuento(monto);
        montoConDescuento = Math.round(montoConDescuento * 10.0) / 10.0;
        assertEquals(monto, montoConDescuento, 0);
    }
}
