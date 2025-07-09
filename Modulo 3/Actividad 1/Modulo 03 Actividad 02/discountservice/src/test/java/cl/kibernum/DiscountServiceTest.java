package cl.kibernum;

import static org.junit.Assert.assertEquals;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;


public class DiscountServiceTest 
{

     DiscountService discountService = new DiscountService();
     Random random = new Random();

    @Test
    public void testDescuento15Porciento() {
        double monto = ThreadLocalRandom.current().nextDouble(100000.1, 900000);
        monto = Math.round(monto * 10.0) / 10.0;
        System.out.println(monto);
        double montoConDescuento = discountService.calcularDescuento(monto);
        montoConDescuento = Math.round(montoConDescuento * 10.0) / 10.0;
        System.out.println(montoConDescuento);
        assertEquals((monto * 0.85), montoConDescuento, 900000);
    }

    @Test
    public void testNoDescuentoBajo50000() {
        double monto = ThreadLocalRandom.current().nextDouble(1, 49999.9);
        monto = Math.round(monto * 10.0) / 10.0;
        System.out.println(monto);
        double montoConDescuento = discountService.calcularDescuento(monto);
        montoConDescuento = Math.round(montoConDescuento * 10.0) / 10.0;
        System.out.println(montoConDescuento);
        assertEquals(monto, montoConDescuento, 0);
    }

    @Test
    public void testDescuento10Porciento10() {
        double monto = ThreadLocalRandom.current().nextDouble(50000, 100000);
        monto = Math.round(monto * 10.0) / 10.0;
        System.out.println(monto);
        double montoConDescuento = discountService.calcularDescuento(monto);
        montoConDescuento = Math.round(montoConDescuento * 10.0) / 10.0;
        System.out.println(montoConDescuento);
        assertEquals((monto * 0.9), montoConDescuento, 100000);
    }


}
