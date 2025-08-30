package cl.kibernum.apirest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.kibernum.apirest.dto.ProductoDTO;
import cl.kibernum.apirest.exception.ResourceNotFoundException;
import cl.kibernum.apirest.model.Producto;
import cl.kibernum.apirest.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {
  private final IProductoRepository productoRepository;

  public ProductoServiceImpl(IProductoRepository productoRepository) {
    this.productoRepository = productoRepository;
  }

  @Override
  public List<Producto> findAll() {
    return (List<Producto>) this.productoRepository.findAll();
  }

  @Override
  public List<Producto> findAllByActiveTrue() {
    return StreamSupport.stream(productoRepository.findAll().spliterator(), false)
                          .filter(p -> p.isActive())
                          .collect(Collectors.toList());
  }

  @Override
  public Optional<Producto> findById(Long id) {
    return this.productoRepository.findById(id);
  }
  
  @Override
  @Transactional
  public Producto create(ProductoDTO productoDTO) {
    Producto producto = new Producto();
    producto.setName(productoDTO.getName());
    producto.setDescription(productoDTO.getDescription());
    producto.setImage(productoDTO.getImage());
    producto.setStock(productoDTO.getStock());
    producto.setPrice(productoDTO.getPrice());
    producto.setActive(productoDTO.isActive());
    return this.productoRepository.save(producto);
  }

  @Override
  @Transactional
  public Producto update(Long id, ProductoDTO productoDTO) {
    Producto existingProducto = this.productoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    existingProducto.setName(productoDTO.getName());
    existingProducto.setDescription(productoDTO.getDescription());
    existingProducto.setImage(productoDTO.getImage());
    existingProducto.setStock(productoDTO.getStock());
    existingProducto.setPrice(productoDTO.getPrice());
    existingProducto.setActive(productoDTO.isActive());
    return this.productoRepository.save(existingProducto);
  }
  
  @Override
  @Transactional
  public void delete(Long id) {
    Producto existingProducto = this.productoRepository.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    existingProducto.setActive(false);
  }
}