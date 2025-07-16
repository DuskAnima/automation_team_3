package cl.kibernumacademy.services;

import java.util.ArrayList;
import java.util.List;

import cl.kibernumacademy.models.Task;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;
    
    public void addTask(String title, String description) {
        Task newTask = new Task(nextId++, title, description);
        tasks.add(newTask);
        }
        

    public void markAsDone(Task task) {
        task.setState(true);
    } 

    public List<Task> getUndoneTasks() {
        List<Task> undoneList = new ArrayList<>();
        for (Task task : tasks){
            if (task.getState() == false){
                undoneList.add(task);
            }
        }
        return undoneList;
    }
        /*
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

    // Función privada encapsulada por ser de uso reiterativo. Previene cualquier retorno de objeto vacío
    private Task getTaskById(int id) { 
        return tasks.stream().
            filter(task -> task.getId() == id).
            findFirst().
            orElseThrow(() -> new IllegalArgumentException("Esta tarea no existe"));
    }
    */

    public List<Task> getList() {
        return tasks;
    }
}