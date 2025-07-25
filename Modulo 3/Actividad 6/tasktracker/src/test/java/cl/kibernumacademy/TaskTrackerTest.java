package cl.kibernumacademy;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import cl.kibernumacademy.services.TaskTracker;
import cl.kibernumacademy.models.Task;

public class TaskTrackerTest {
    private TaskTracker tracker;

    @BeforeClass
    public void setUpClass() {
        tracker = new TaskTracker();
    }

    @AfterClass
    public void tearDownClass() {
        tracker = null;
    }

    @BeforeMethod
    public void clearTasks() {
        tracker.getList().clear();
    }

    @Test
    @Parameters({"title", "description"})
    public void testAddTask(String title, String description) {
        // Verifica que inicie sin tareas
        Assert.assertTrue(tracker.getList().isEmpty(), "La lista debe iniciar vacía");

        // Agrega una nueva tarea
        tracker.addTask(title, description);
        List<Task> tasks = tracker.getList();
        Task task = tasks.get(0);

        // Validaciones con SoftAssert
        SoftAssert soft = new SoftAssert();
        soft.assertNotNull(task, "La tarea no debe ser nula");
        soft.assertEquals(tasks.size(), 1, "Debe haber exactamente una tarea");
        soft.assertEquals(task.getTitle(), title, "El título debe coincidir");
        soft.assertEquals(task.getDescription(), description, "La descripción debe coincidir");
        soft.assertEquals(task.getState(), Task.State.ACTIVE, "Estado inicial debe ser ACTIVE");
        soft.assertAll();
    }

    @Test
    @Parameters({"title", "description"})
    public void testMarkTaskDone(String title, String description) {
        // Agrega y marca tarea como completada
        tracker.addTask(title, description);
        Task task = tracker.getList().get(0);

        tracker.markAsDone(task);
        Assert.assertEquals(task.getState(), Task.State.INACTIVE,
                "Estado debe ser INACTIVE después de marcar como hecha");
    }
    

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMarkTaskDoneInvalid() {
        // Al pasar null, debe lanzar IllegalArgumentException
        tracker.markAsDone(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testMarkAlreadyDoneTask() {
        tracker.addTask("Tarea", "desc");
        Task task = tracker.getList().get(0);
        tracker.markAsDone(task); // la marca como INACTIVE

        // Segundo intento debe fallar
        tracker.markAsDone(task);
    }

    @Test
    public void testAddTaskWithNullOrEmptyTitle() {
        tracker.addTask(null, "desc");
        tracker.addTask("", "desc");

        Assert.assertTrue(tracker.getList().isEmpty(), "No se debe agregar ninguna tarea si el título es inválido");
    }

    @Test
    public void testGetUndoneTasksFiltering() {
        // Agrega varias tareas y marca la primera como hecha
        tracker.addTask("T1", "Desc1");
        tracker.addTask("T2", "Desc2");
        tracker.addTask("T3", "Desc3");
        Task done = tracker.getList().get(0);
        tracker.markAsDone(done);

        // Obtiene solo las tareas pendientes
        List<Task> undone = tracker.getUndoneTasks();

        SoftAssert soft = new SoftAssert();
        // Verifica que todas las tareas filtradas estén activas
        for (Task t : undone) {
            soft.assertEquals(t.getState(), Task.State.ACTIVE,
                    "Todas las tareas retornadas deben estar en estado ACTIVE");
        }
        // Verifica conteo de tareas pendientes
        soft.assertEquals(undone.size(), 2, "Deben quedar 2 tareas pendientes");
        soft.assertAll();
    }
}
