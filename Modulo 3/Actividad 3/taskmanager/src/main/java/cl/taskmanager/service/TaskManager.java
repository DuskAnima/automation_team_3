package cl.taskmanager.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.taskmanager.model.Task;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task createTask(String header, String paragraph) { // Crear una tarea
        Task task = new Task(nextId++, header, paragraph); // Invoca método constructor de tarea
        tasks.add(task); // Agrega tarea a la lista
        return task;  // Retorna tarea para testing
    }

    public List<Task> getList() { // Obtener la lista
        return Collections.unmodifiableList(tasks); // Retorna una lista inmutable
    }

    public boolean updateHeader(int id, String newHeader) { // Actualizar el título de la tarea
        try {
            Task task = getTaskById(id); // Invoca la tarea desde el Id
            if (newHeader != null) { // Verifica nullidad
                task.setHeader(newHeader); // Si el valor es null, el valor queda como estaba en un comienzo
            }
            return true; // True == tarea exitosa 
        } catch (IllegalArgumentException e) { // Manejo de error
            return false; // Ciclo fallido, check de aviso al test
        }
    }

    public boolean updateParagraph(int id, String newParagraph) { // Actualizar texto de la tarea
        try {   
            Task task = getTaskById(id); // Flujo igual al método de título
            if (newParagraph != null) {
                task.setParagraph(newParagraph);
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean deleteTask(int id) { // Eliminar tarea
        try {
            Task task = getTaskById(id); // Captura tarea desde el Id
            tasks.remove(task); // Elimina la tarea
            return true;
        } catch (IllegalArgumentException e) { // Manejo de error
            return false;
        }
    }

    private Task getTaskById(int id) { // Encuentrar tarea con el Id
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));
    }
}