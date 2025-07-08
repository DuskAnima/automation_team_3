package cl.kibernaumacademy;

import java.util.ArrayList;
import java.util.List;


   public class TaskManager
{

        private List<Tarea> tareas;
        private int id;

        public TaskManager() {
        this.tareas = new ArrayList<>();
        this.id = 0;
    }

    public void agregarTarea(String titulo, String descripcion) {
        Tarea tarea = new Tarea(id++, titulo, descripcion);
        tareas.add(tarea);
    }

    public void actualizarTarea(int id, String nuevoTitulo, String nuevaDescripcion) {
        Tarea tarea = buscarTareaPorId(id);
        tarea.setTitulo(nuevoTitulo);
        tarea.setDescripcion(nuevaDescripcion);
    }

    public void eliminarTarea(int id) {
        Tarea tarea = buscarTareaPorId(id);
        tareas.remove(tarea);
    }

    private Tarea buscarTareaPorId(int id) {
        return tareas.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada con ID: " + id));
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
}



    


