package cl.kibernum.tienda.service;

import cl.kibernum.tienda.model.Producto;

public interface ServicioDescuento {
  double aplicarDescuento(Producto producto);
}
