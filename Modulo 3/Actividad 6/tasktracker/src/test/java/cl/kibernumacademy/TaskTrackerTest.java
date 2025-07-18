package cl.kibernumacademy;

import java.util.List;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.*;

import cl.kibernumacademy.models.Task;
import cl.kibernumacademy.services.TaskTracker;

public class TaskTrackerTest {
    private TaskTracker tracker; // servicio
    public static int counter;
    @BeforeMethod
    void StartSetUp() {
        System.out.println("Inicio de Test Unitario " + ++counter);
        tracker = new TaskTracker(); 
    }

    @AfterClass
    void tearDown() {
        System.out.println("Fin del Test Unitario");
    }


    @Test (description = "Prueba de agregación de tareas")
    void shouldAddTaskToAList() {
        tracker.addTask("Comprar", "comprar en el supermercado lacteos", true);
        Task task = tracker.getList().get(0); // Obtiene la tarea agregada
        Assert.assertNotNull(task, "La tarea no puede ser nula"); // Verifica no nulidad
        Assert.assertTrue(tracker.getList().contains(task), "La tarea debe estar en la lista");
}

    @Test    
    @Parameters({"title", "description", "state"})
    public void shouldUpdateTask(String title, String description, String state) {
         boolean parsedState = Boolean.parseBoolean(state);
         tracker.addTask("Pintar", "Pintar la casa", parsedState);
         Task task = tracker.getList().get(0);
         Assert.assertNotNull(task, "La tarea no puede ser nula");

         tracker.updateTitle(task.getId(), title);
         Assert.assertEquals(task.getTitle(), title, "El título no se actualizó correctamente.");

         tracker.updateDescription(task.getId(), description);
         Assert.assertEquals(task.getDescription(), description, "La descripción no se actualizó correctamente.");

         tracker.updateState(task.getId(), parsedState);
        Assert.assertEquals(task.getState(), parsedState, "El estado no se actualizó correctamente.");

         Assert.assertTrue(tracker.getList().contains(task), "La tarea debe estar en la lista");
}

    
    @Test
    void shouldDeleteTaskById() {
        tracker.addTask("Pintar", "Pintar la casa", true);

        int taskId = tracker.getList().get(0).getId();
        tracker.deleteTask(taskId);
            
    }

    @Test
    public void shouldFilterTasks() {
        SoftAssert softAssert = new SoftAssert();
        List<Task> completed = tracker.filterTasks(true);
        List<Task> pending = tracker.filterTasks(false);

        for (Task task : completed) {
            softAssert.assertTrue(task.getState(), "Tarea marcada como completada no lo está.");
        }
        for (Task task : pending) {
            softAssert.assertFalse(task.getState(), "Tarea pendiente aparece como completada.");
        }
        softAssert.assertAll();
    }
}
