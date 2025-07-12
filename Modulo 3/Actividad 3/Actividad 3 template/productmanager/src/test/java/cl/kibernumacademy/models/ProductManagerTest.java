package cl.kibernumacademy.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
    shouldAddProductToAList() {
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990);
        Product product =  manager.getList().get(0); // Acceso a la tarea agregada desde la lista.

        assertNotNull(product, "El producto no puede ser nulo"); // Verifica que se haya creado la tarea
        assertThat(manager.getList(), hasSize(1)); // Verifica que la tarea fue agregada a la lista
        assertThat(product, allOf(      // Verifica asignación de atributos del producto
            hasProperty("name", is("Comida etapa 1")),
            hasProperty("description", is("de 1 hasta 6 meses")),
            hasProperty("price", is(19990))
        ));
    }

    @DisplayName("Prueba de modificación de productos")
    @Test
    void shouldUpdateProduct() {
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990);
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990);
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990);
        Product producto = manager.getProductByID(1);

        boolean updateName = manager.updateName(producto, "Comida primeros años");
        boolean updateDescription = manager.updateDescription(producto, "Para recién nacidos");
        boolean updatePrice = manager.updatePrice(producto, 18990);
        assertThat(null, updatePrice);

        // Permitir modificar nombre, descripción o precio
        // Validación de que el producto existe
    }

    @DisplayName("Prueba de eliminación de productos")
    @Test
    void shouldDeleteProduct() {
        // Eliminar producto desdde su Id
        // Validar que el producto existe
    }
}
