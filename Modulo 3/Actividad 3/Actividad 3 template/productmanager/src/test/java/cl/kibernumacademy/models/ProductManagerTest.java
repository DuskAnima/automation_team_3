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
        Product product = manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990);
        assertNotNull(product.getId(), "El producto no puede ser nulo"); // Verifica que se haya creado la tarea
        assertThat(manager.getList()).hasSize(1); // Verifica que la tarea fue agregada a la lista

        // Cada producto debe tener nombre, descripción y precio.
        // Debe almacenarse en una lista
    }

    @DisplayName("Prueba de modificación de productos")
    @Test
    shouldUpdateProduct() {
        Product producto = manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990);
        boolean updateName = manager.updateName(producto.getId(), "Comida primeros años");
        boolean updateDescription = manager.updateDescription(producto.getId(), "Para recién nacidos");
        boolean updatePrice = manager.updatePrice(producto.getId(), 18990);
        assertThat(null, updatePrice);

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
