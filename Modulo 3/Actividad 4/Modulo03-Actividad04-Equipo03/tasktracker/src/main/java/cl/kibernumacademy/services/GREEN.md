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

    public List<Task> getList() {
        return tasks;
    }
}