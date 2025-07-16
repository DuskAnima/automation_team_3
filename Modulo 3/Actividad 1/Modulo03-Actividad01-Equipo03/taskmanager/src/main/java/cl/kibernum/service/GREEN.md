package cl.kibernum.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.kibernum.model.Task;

public class TaskManager { 
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task createTask(String header, String paragraph) {
        Task task = new Task(nextId++, header, paragraph);
        tasks.add(task);
        return task;
    }

    public List<Task> getList() {
        return Collections.unmodifiableList(tasks);
    }

    public boolean updateHeader(int id, String newHeader) {
        try {
            Task task = getTaskById(id);
            if (newHeader != null) {
                task.setHeader(newHeader);
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean updateParagraph(int id, String newParagraph) {
        try {
            Task task = getTaskById(id);
            if (newParagraph != null) {
                task.setParagraph(newParagraph);
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean deleteTask(int id) {
        try {
            Task task = getTaskById(id);
            tasks.remove(task);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new IllegalArgumentException("Tarea no encontrada con ID: " + id);
    }
}