package cl.kibernum;

/**
 * Hello world!
 *
 */
public class DiscountService 
{
    public double calcularDescuento(double monto) {
        if (monto <= 0) { // Numero negativo error
            throw new IllegalArgumentException("El monto no puede ser negativo");
        } else if (monto > 100000) { // Si monto mayor a 100k
            return monto * 0.85; // 15% de descuento (retorna 85%)
        } else if (monto >= 50000) { // Si monto mayor a 50k
            return monto * 0.90; // 10% de desciento (retorna 90%)
        } else {
            return monto; // Monto menor 49.999, precio completo
        }
    }
}
