package cl.kibernum.apirest.service;

import java.util.List;
import java.util.Optional;

import cl.kibernum.apirest.dto.ProductoDTO;
import cl.kibernum.apirest.model.Producto;

public interface IProductoService {
  List<Producto> findAll();
  List<Producto> findAllByActiveTrue();
  Optional<Producto> findById(Long id);
  Producto create(ProductoDTO productoDTO);
  Producto update(Long id, ProductoDTO productoDTO);
  void delete(Long id);
}