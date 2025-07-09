package cl.kibernum;

/**
 * Hello world!
 *
 */
public class DiscountService 
{
    public double calcularDescuento(double monto) {
        if (monto <= 0){
            throw new IllegalArgumentException("El monto no puede ser negativo");
        } else if (monto > 100000) {
            return monto * 0.85; 
        } else if (monto >= 50000) {
            return monto * 0.90; 
        } else {
            return monto; 
        }
    }
}
