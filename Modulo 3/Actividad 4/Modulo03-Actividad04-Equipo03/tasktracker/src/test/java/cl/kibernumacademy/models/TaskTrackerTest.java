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

import cl.kibernumacademy.services.TaskTracker;

public class TaskTrackerTest {
    private TaskTracker tracker; // servicio
    public static int counter;
    @BeforeEach
    void StartSetUp() {
        System.out.println("Inicio de Test Unitario " + ++counter);
        tracker = new TaskTracker(); 
    }

    @AfterEach
    void tearDown() {
        System.out.println("Fin del Test Unitario");
    }

// Implementar pruebas assert con JUnit y Hamcrest. Usar assumeTrue() o assumeThat(). Integrar @ParameterizedTest, @BeforeEach y @AfterEach  
    @DisplayName("Prueba de agregación de tareas")
    @Test 
    void shouldAddTaskToAList() {
    assertThat(tracker.getList(), iterableWithSize(0)); // Validación de lista vacía.
    
    tracker.addTask("Comprar", "comprar en el supermercado lacteos", false); // Agrega una tarea
    var task = tracker.getList().get(0); // Obtiene la tarea agregada

    assertNotNull(task, "La tarea no puede ser nula"); // Verifica que la tarea no es null
    assertThat(tracker.getList(), hasSize(1)); // Verifica que la tarea fue agregada a la lista
    assertThat(task.getTitle(), is("Comprar"));
    assertThat(task.getDescription(), is("comprar en el supermercado lacteos"));
    assertThat(task.getState(), is(false));
}

    @DisplayName("Prueba de modificación de tareas")
    @ParameterizedTest
    @CsvSource ({
        "Cambio de titulo, NuevoTitulo",
        "Cambio de descripción, Nueva descripcion",
        "Cambio de estado, true"
    })
    void shouldUpdateTask(String test, String attributesToUpdate) {
        tracker.addTask("Pintar", "Pintar la casa", true);
        var task = tracker.getList().get(0);

        // Verifica la no nulidad de la tarea
        assertNotNull(task, "La tarea no puede ser nula");

        // Verifica la integridad de los cambios de datos correspondientes
        assumingThat(test.equals("Cambio de titulo"), () -> {
            tracker.updateTitle(task.getId(), attributesToUpdate);
            assertThat(task, hasProperty("title", is(attributesToUpdate))); 
        });
        assumingThat(test.equals("Cambio de descripción"), () -> {
            tracker.updateDescription(task.getId(), attributesToUpdate);
            assertThat(task.getDescription(), is(attributesToUpdate));
        });
        assumingThat(test.equals("Cambio de estado"), () -> {
            tracker.updateState(task.getId(), Boolean.parseBoolean(attributesToUpdate));
            assertThat(task.getState(), is(Boolean.parseBoolean(attributesToUpdate)));
        }); 
    }

    @DisplayName("Prueba de eliminación de tareas")
    @Test
    void shouldDeleteTaskById() {
        tracker.addTask("Pintar", "Pintar la casa", true);
        assertThat(tracker.getList(), iterableWithSize(1)); // Validación de lista con 1 objeto.
        int taskId = tracker.getList().get(0).getId();
        
        // Verifica la eliminación de la tarea
        tracker.deleteProduct(taskId);
        assertThat(tracker.getList(), iterableWithSize(0));



public class TaskManagerTest {
    TaskManager manager;

    @BeforeEach
    private void setUp() {
        manager = new TaskManager();
    }

    // TOdO: assertions de JUnit y Hamcrest. assumeTrue o assumingThat. ParametrizedTest. Before/After.Each
    @DisplayName("Prueba de agregación de tareas. (title, description, state = false)")
    @Test 
    void shouldAddNewUnfinishedTask() {
        
    }

    @DisplayName("Prueba de chack de tarea completada (state = true)")
    @Test
    void shouldMarkATaskAsDone() {

    }
    
    @DisplayName("Prueba de retorno de solo tareas pendientes")
    @Test
    void shouldShowOnlyPendingTasksAsAList(){

    }

}

    }
}
