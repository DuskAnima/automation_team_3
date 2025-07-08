package cl.kibernaumacademy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class AppTest 
{
    @BeforeEach
    public void testAgregarTarea() {
        TaskManager tm = new TaskManager();
        tm.agregarTarea("Comprar leche", "Ir al super");
        assertEquals(1, tm.getTareas().size());
    }

    @Test
    public void testActualizarTarea() {
        TaskManager tm = new TaskManager();
        tm.agregarTarea("Leer", "Leer libro");
        tm.actualizarTarea(0, "Leer novela", "Leer capítulo 4");
        assertEquals("Leer novela", tm.getTareas().get(0).getTitulo());
    }

    @Test
    public void testActualizarTareaInexistente() {
        TaskManager tm = new TaskManager();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tm.actualizarTarea(999, "Nada", "Nada");
        });
        assertTrue(exception.getMessage().contains("no encontrada"));
    }

    @Test
    public void testEliminarTarea() {
        TaskManager tm = new TaskManager();
        tm.agregarTarea("Llamar", "Llamar a mamá");
        tm.eliminarTarea(0);
        assertEquals(0, tm.getTareas().size());
    }

    @Test
    public void testEliminarTareaInexistente() {
        TaskManager tm = new TaskManager();
        assertThrows(IllegalArgumentException.class, () -> {
            tm.eliminarTarea(123);
        });
    }
}
