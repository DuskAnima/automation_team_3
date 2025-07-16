package cl.kibernumacademy.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import cl.kibernumacademy.services.TaskManager;

public class TaskManagerTest {
    TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = new TaskManager();
    }

    @AfterEach
    void tearDown() {
        manager = null;
    }

    // TOdO: assertions de JUnit y Hamcrest. assumeTrue o assumingThat. ParametrizedTest. Before/After.Each
    @DisplayName("Prueba de agregación de tareas. (title, description, state = false)")
    @ParameterizedTest
    @CsvSource({"Comprar, Comprar lacteos en el sueprmercado."})
        void shouldAddNewUnfinishedTask(String title, String description) {
        assertThat(manager.getList(), iterableWithSize(0)); // Validación de lista vacía.
        
        manager.addTask(title, description); // Agrega una tarea. Estado false por defecto
        var task = manager.getList().get(0); // Obtiene la tarea agregada

        assertNotNull(task, "La tarea no puede ser nula"); // Verifica que la tarea no es null
        assertThat(manager.getList(), hasSize(1)); // Verifica que la tarea fue agregada a la lista
        assertThat(task.getTitle(), is(title));
        assertThat(task.getDescription(), is(description));
        assertThat(task.getState(), is(false));        
        }

    @DisplayName("Prueba de chack de tarea completada (state = true)")
    @ParameterizedTest
    @CsvSource({
        "Pintar, Pintar la casa", // Valor funcional
        ",", // Valor erroneo. String vacíos
    })
    void shouldMarkATaskAsDone(String name, String description) {
        manager.addTask(name, description);

        List<Task> list = manager.getList();

        // Verifica la integridad de los cambios de datos correspondientes
        assumingThat(!list.isEmpty(), () -> { // Si la lista no está vacía
            var task = list.get(0); 
            manager.markAsDone(task);
            assertThat(task, hasProperty("state", is(true))); 
        });
        assumingThat(list.isEmpty(), () -> { // Verifica que lista está vacía
            assertThrows(IllegalArgumentException.class, () -> {
                manager.markAsDone(0);
            });
        });
    }
    
    @DisplayName("Prueba de retorno de solo tareas pendientes")
    @Test
    void shouldShowOnlyPendingTasksAsAList(){
        manager.addTask("Título 1", "Tarea 1 (lista)");
        manager.addTask("Título 2", "Tarea 2"); // Se crean dos tareas
        manager.addTask("Título 3", "Tarea 3"); // Se crean dos tareas
        Task doneTask = manager.getList().get(0);
        doneTask.markAsDone(); // Marcar una tarea como hecha
        List<Task> undoneList = manager.getUndoneTasks();
        
        assertThat(doneTask, hasProperty("state", is(true))); // Verificar tarea hecha
        for (Task task : undoneList) {
            assertThat(task, hasProperty("state", is(false)));
        }
    }
}