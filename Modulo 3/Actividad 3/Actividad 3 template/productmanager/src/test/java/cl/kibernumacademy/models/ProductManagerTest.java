package cl.kibernumacademy.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import cl.kibernumacademy.services.ProductManager;

public class ProductManagerTest {
    private ProductManager manager; // servicio
    public static int counter;
    @BeforeEach
    void StartSetUp() {
        System.out.println("Inicio de Test Unitario " + ++counter);
        manager = new ProductManager(); 
    }

    @AfterEach
    void tearDown() {
        System.out.println("Fin del Test Unitario");
    }

// Implementar pruebas assert con JUnit y Hamcrest. Usar assumeTrue() o assumeThat(). Integrar @ParameterizedTest, @BeforeEach y @AfterEach  
    @DisplayName("Prueba de agregación de productos")
    @Test 
    void shouldAddProductToAList() {
        assertThat(manager.getList(), iterableWithSize(0)); // Validación de lista vacía.
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990.0); // Producto agregado por medio del manager
        var product =  manager.getList().get(0); // Acceso a la tarea agregada desde la lista.

        assertNotNull(product, "El producto no puede ser nulo"); // Verifica que la tarea creada está en lista.
        assertThat(manager.getList(), hasSize(1)); // Verifica que la tarea fue agregada a la lista.
        assertThat(product, allOf(      // Verifica asignación de atributos del producto.
            hasProperty("name", is("Comida etapa 1")),
            hasProperty("description", is("de 1 hasta 6 meses")),
            hasProperty("price", is(19990.0))
        ));
    }

    @DisplayName("Prueba de modificación de productos")
    @ParameterizedTest
    @CsvSource ({
        "Cambio de nombre, Leche para lactantes",
        "Cambio de descripción, Recomendada para recién nacidos",
        "Cambio de precio, 20990.0"
    })
    void shouldUpdateProduct(String test, String attributesToUpdate) {
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990.0);
        var product = manager.getList().get(0);

        // Verifica la no nulidad del producto
        assertNotNull(product, "El producto no puede ser nulo");

        // Verifica la integridad de los cambios de datos correspondientes
        assumingThat(test.equals("Cambio de nombre"), () -> {
            manager.updateName(product.getId(), attributesToUpdate);
            assertThat(product, hasProperty("name", is(attributesToUpdate))); 
        });
        assumingThat(test.equals("Cambio de descripción"), () -> {
            manager.updateDescription(product.getId(), attributesToUpdate);
            assertThat(product, hasProperty("description", is(attributesToUpdate)));
        });
        assumingThat(test.equals("Cambio de precio"), () -> {
            manager.updatePrice(product.getId(), Double.parseDouble(attributesToUpdate));
            assertThat(product, hasProperty("price", is(Double.parseDouble(attributesToUpdate))));
        }); // Este es un ejemplo un poco hardcoded, pero sirve para demostrar el uso de assumingThat()
    }

    @DisplayName("Prueba de eliminación de productos")
    @Test
    void shouldDeleteProductById() {
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990.0);
        assertThat(manager.getList(), iterableWithSize(1)); // Validación de lista con 1 objeto.
        int productId = manager.getList().get(0).getId();
        
        // Verifica la eliminación del producto
        manager.deleteProduct(productId);
        assertThat(manager.getList(), iterableWithSize(0));
    }
}
