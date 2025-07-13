package cl.kibernumacademy.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ProductManagerTest {
    private ProductManager manager; // servicio

    @BeforeEach
    void StartSetUp() {
        System.out.println("Inicio de Test Unitario");
        manager = new ProductManager(); 
    }

    @AfterEach
    void EndSetUp() {
        System.out.println("Fin del Test Unitario");
    }

// Implementar pruebas assert con JUnit y Hamcrest. Usar assumeTrue() o assumeThat(). Integrar @ParameterizedTest, @BeforeEach y @AfterEach  
    @DisplayName("Prueba de agregación de productos")
    @Test 
    void shouldAddProductToAList() {
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990); // Producto agregado por medio del manager
        Product product =  manager.getList().get(0); // Acceso a la tarea agregada desde la lista.

        assertNotNull(product, "El producto no puede ser nulo"); // Verifica que la tarea creada está en lista.
        assertThat(manager.getList(), hasSize(1)); // Verifica que la tarea fue agregada a la lista.
        assertThat(product, allOf(      // Verifica asignación de atributos del producto.
            hasProperty("name", is("Comida etapa 1")),
            hasProperty("description", is("de 1 hasta 6 meses")),
            hasProperty("price", is(19990))
        ));
    }

    @DisplayName("Prueba de modificación de productos")
    @ParameterizedTest
    @CsvSource ({
        "Cambio de nombre, Leche para lactantes",
        "Cambio de descripción, Recomendada para recién nacidos",
        "Cambio de precio, 20990"
    })
    void shouldUpdateProduct(String test, String attributesToUpdate) {
        manager.addProduct("Comida etapa 1", "de 1 hasta 6 meses", 19990);
        Product product = manager.getList().get(0);

        // Verifica la no nulidad del producto
        assertNotNull(product, "El producto no puede ser nulo");
        
        // Verifica la integridad de los cambios de datos correspondientes
        assumingThat(test.equals("Cambio de nombre"), () -> {
            manager.updateName(product.getId(), attributesToUpdate);
            assertThat(product,hasProperty("name", is(attributesToUpdate))); 
        });
        assumingThat(test.equals("Cambio de descripción"), () -> {
            manager.updateDescription(product.getId(), attributesToUpdate);
            assertThat(product,hasProperty("description", is("Recomendada para recién nacidos")));
        });
        assumingThat(test.equals("Cambio de precio"), () -> {
            manager.updatePrice(product.getId(), Integer.parseInt(attributesToUpdate));
            assertThat(product,hasProperty("price", is(20990)));
        }); // Este es un ejemplo un poco hardcoded, pero sirve para demostrar el uso de assumingThat()
    }

    @DisplayName("Prueba de eliminación de productos")
    @Test
    void shouldDeleteProduct() {
        // Eliminar producto desdde su Id
        // Validar que el producto existe
    }

}
