package cl.kibernumacademy.services;

import java.util.ArrayList;
import java.util.List;

import cl.kibernumacademy.models.Task;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;
    
    public void addTask(String title, String description) {
        if(title == null || title.isEmpty()) {return;}
        Task newTask = new Task(nextId++, title, description);
        tasks.add(newTask);
        }
        

    public void markAsDone(Task task) {
        if(task == null || task.getState() ) {
            throw new IllegalArgumentException();
        } else {
            task.setState(true);
        }
    } 

    public List<Task> getUndoneTasks() {
        return tasks.stream()
        .filter(task -> !task.getState())
        .toList();
    }

    public List<Task> getList() {
        return tasks;
    }
}