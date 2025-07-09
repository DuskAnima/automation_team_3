package com.example;





import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import org.junit.Test;



import com.example.TareaTest;

public class TareaTest {
    private TareaManager manager;

     @BeforeEach
    void setUp() {
        manager = new TareaManager();
    }


    //----------agregar tarea--------//
    @Test
    void crearTarea(){
        Tarea   inv =  manager.crearTarea(1,"<Los Dinosaurios","Trabajo de Investigación del Dr. Jaime Castillo");
        assertThat(inv.getId()).isEqualTo(1);
        assertThat(manager.getAll()).hasSize(1);

    }

    //------------actualizar Tarea ------------//
    @Test
    void actualizarTarea() {
        Tarea inv  = manager.crearTarea(1,"Los Dinosaurios", "Trabajo de Investigación del Dr. Jaime Castillo");
        boolean updated = manager.updateTitulo(inv.getId(), "Los Dinosaurios 2025");
        assertThat(updated).isTrue();
        assertThat(inv.getTitulo()).isEqualTo("Los Dinosaurios 2025");
    }

    //------------eliminar tarea ------------------//
     @Test
    void eliminarTarea() {
        Tarea inv  = manager.crearTarea(1,"Los Dinosaurios", "Trabajo de Investigación del Dr. Jaime Castillo");
        boolean removed = manager.deleteTarea(inv.getId());
        assertThat(removed).isTrue();
        assertThat(manager.getAll()).isEmpty();
  }



}