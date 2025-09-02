package cl.kibernum.tienda.negocio;

import java.util.List;
import java.util.stream.Collectors;

import cl.kibernum.tienda.model.Producto;
import cl.kibernum.tienda.service.ServicioDescuento;

public class GestorProductos {
  ServicioDescuento servicioDescuento;

  public GestorProductos(ServicioDescuento servicioDescuento) {
    this.servicioDescuento = servicioDescuento;
  }

  public List<Producto> aplicarDescuento(List<Producto> productos) {
    return productos.stream()
      .map(p -> new Producto(p.getNombre(), servicioDescuento.aplicarDescuento(p)))
      .collect(Collectors.toList());
  }
}
