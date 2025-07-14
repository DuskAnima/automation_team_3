package cl.kibernumacademy.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
