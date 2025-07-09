package cl.taskmanager.service;

import org.junit.jupiter.api.*;

import cl.taskmanager.model.Task;

import static org.assertj.core.api.Assertions.*;


public class TaskManagerTest {
    private TaskManager manager; // Se crea el servicio

    @BeforeEach
    void setUp() {
        manager = new TaskManager();
    }

    @Test // Pruebas de creación de tareas
    void shouldCreateTask() {
        Task task = manager.createTask("Lorem ipsum", "Dolor sit amet"); // Modelo
        assertThat(task.getId()).isEqualTo(1); // La tarea fue creada?
        assertThat(manager.getList()).hasSize(1);  // La tarea fue agregada a la lista?
    }

    @Test // Prueba de edición de tareas
    void shouldUpdateTask() {
        Task task = manager.createTask("Lorem ipsum", "Dolor sit amet");  // Modelo 
        boolean updateHeader = manager.updateHeader(task.getId(), "Nuevo Título"); // Se altera título
        boolean updateParagraph = manager.updateParagraph(task.getId(), "Nuevo Párrafo"); // Se altera descripción
        assertThat(updateHeader).isTrue(); // El header fue actualizado?
        assertThat(updateParagraph).isTrue(); // El párrafo fue actualizado?
        assertThat(task.getHeader()).isEqualTo("Nuevo Título"); // Devuelve título de la tarea?
        assertThat(task.getParagraph()).isEqualTo("Nuevo Párrafo"); // Devuelve párrafo de la tarea?
    }

    @Test // Prueba de eliminación de tareas
    void shouldDeleteTask() {
        Task task = manager.createTask("Lorem ipsum", "Dolor sit amet"); // Modelo
        boolean removed = manager.deleteTask(task.getId()); // Se elimina tarea de la lista
        assertThat(removed).isTrue(); // La tarea fue eliminada? 
        assertThat(manager.getList()).isEmpty(); // La lista está vacía?
    }
} 
