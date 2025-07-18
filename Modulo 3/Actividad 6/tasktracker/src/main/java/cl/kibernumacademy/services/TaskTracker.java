package cl.kibernumacademy.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import cl.kibernumacademy.models.Task;

public class TaskTracker {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public void addTask(String title, String description, boolean state) {
        if (title   == null || title.isBlank()) throw new IllegalArgumentException("Titulo inválido");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("Descripción inválida");
        Task newTask = new Task(nextId++, title, description, state);
        tasks.add(newTask);
    }

    public void updateTitle(int id, String newTitle) {
        if (newTitle   == null || newTitle.isBlank()) throw new IllegalArgumentException("Titulo inválido");
        Task task = getTaskById(id);
        task.setTitle(newTitle);
    }

    public void updateDescription(int id, String newDescription) {
        if (newDescription == null || newDescription.isBlank()) throw new IllegalArgumentException("Descripción inválida");
        Task task = getTaskById(id);
        task.setDescription(newDescription);
    }

    public void updateState(int id, boolean newState) {
        Task task = getTaskById(id);
        task.setState(newState);
    }

    public void deleteProduct(int id) {
        Task task = getTaskById(id);
        tasks.remove(task);
    }

    public List<Task> filterTasks(boolean completed) {
        return tasks.stream()
                .filter(task -> task.getState() == completed)
                .collect(Collectors.toList());
    }


    // Función privada encapsulada por ser de uso reiterativo. Previene cualquier retorno de objeto vacío
    private Task getTaskById(int id) { 
        return tasks.stream().
            filter(task -> task.getId() == id).
            findFirst().
            orElseThrow(() -> new IllegalArgumentException("Esta tarea no existe"));
    }

    public List<Task> getList() {
        return Collections.unmodifiableList(tasks);
    }
}
