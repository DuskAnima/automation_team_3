package cl.kibernum.tienda.negocio;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;



import cl.kibernum.tienda.model.Producto;
import cl.kibernum.tienda.service.ServicioDescuento;

@ExtendWith(MockitoExtension.class)
public class GestorProductosTest {
  private GestorProductos gestorProductos;

  @Mock
  private ServicioDescuento servicioDescuento;

  @BeforeEach
  public void setUp() {
    gestorProductos = new GestorProductos(servicioDescuento);
  }

  /*
   * Se utiliza thenAnswer() en vez de thenReturn() porque en los requerimientos
   * se especifica que es necesario simular los resultados del servicio,
   * es decir, que los precios hayan sido modificados correctamente y luego
   * que se verifique el resultado de esto pese a que no contamos con esta lógica.
   */

  @Test
  public void DiscountShouldBeAppliedIfProductoPriceIs100OrMore() {
    when(servicioDescuento.aplicarDescuento(any(Producto.class)))  
      .thenAnswer(invoc -> { 
        Producto p = invoc.getArgument(0);
        if (p.getPrecio() > 100) {
          return p.getPrecio() * 0.9;
        } else {
          return p.getPrecio();
        }
      });

    List<Producto> discountList = gestorProductos.aplicarDescuento(productos);

    assertThat(discountList.size(), is(productos.size()));
    assertThat(discountList.get(0).getPrecio(), is(productos.get(0).getPrecio() * 0.90));
    assertThat(discountList.get(1).getPrecio(), is(productos.get(1).getPrecio()));
    assertThat(discountList.get(2).getPrecio(), is(productos.get(2).getPrecio() * 0.90));
    assertThat(discountList.get(3).getPrecio(), is(productos.get(3).getPrecio()));

    verify(servicioDescuento, times(productos.size())).aplicarDescuento(any(Producto.class));
  }

  List<Producto> productos = List.of(
      new Producto("Notebook", 150),
      new Producto("Lapiz", 2),
      new Producto("Automóvil", 1000000),
      new Producto("Zapatos", 99)
  );
}