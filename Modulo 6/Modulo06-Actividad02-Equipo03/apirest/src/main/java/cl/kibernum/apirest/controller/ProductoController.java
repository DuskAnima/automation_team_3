package cl.kibernum.apirest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.kibernum.apirest.dto.ProductoDTO;
import cl.kibernum.apirest.model.Producto;
import cl.kibernum.apirest.service.IProductoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {

  private final IProductoService productoService;

  public ProductoController(IProductoService productoService) { this.productoService = productoService; }

  @PostMapping
  public ResponseEntity<Producto> createProduct(@RequestBody ProductoDTO productoDTO) {
    Producto createProducto = this.productoService.create(productoDTO);
    return new ResponseEntity<>(createProducto, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Producto>> getAllProductos() {
    return ResponseEntity.ok(this.productoService.findAll());
  }

  @GetMapping("/active")
  public ResponseEntity<List<Producto>> getAllProductosByActive() {
    return ResponseEntity.ok(this.productoService.findAllByActiveTrue());
  }

  @PostMapping("/{id}")
  public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
    return ResponseEntity.ok(this.productoService.update(id, productoDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
    this.productoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}