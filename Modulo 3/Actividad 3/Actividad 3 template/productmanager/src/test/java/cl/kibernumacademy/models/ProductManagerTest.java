package cl.kibernumacademy.models;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import static org.hamcrest.MatcherAssert.*;

public class ProductManagerTest {
    private ProductManager manager; // servicio

    @BeforeEach
    void setUp() {
        manager = new ProductManager();
    }

// TODO: Hacer tests para CRUD
// Implementar pruebas assert con JUnit y Hamcrest. Usar assumeTrue() o assumeThat(). Integrar @ParameterizedTest, @BeforeEach y @AfterEach  
    @DisplayName("Prueba de agregación de productos")
    @Test 
    shouldAddProduct() {
        // Cada producto debe tener nombre, descripción y precio.
        // Debe almacenarse en una lista
    }

    @DisplayName("Prueba de modificación de productos")
    @Test
    shouldUpdateProduct() {
        // Permitir modificar nombre, descripción o precio
        // Validación de que el producto existe
    }

    @DisplayName("Prueba de eliminación de productos")
    @Test
    shouldDeleteProduct() {
        // Eliminar producto desdde su Id
        // Validar que el producto existe
    }
}
