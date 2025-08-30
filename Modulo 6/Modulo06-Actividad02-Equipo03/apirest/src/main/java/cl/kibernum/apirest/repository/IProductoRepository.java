package cl.kibernum.apirest.repository;

import org.springframework.data.repository.CrudRepository;

import cl.kibernum.apirest.model.Producto;

public interface IProductoRepository extends CrudRepository<Producto, Long>{
  
}
