package cl.kibernumacademy.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cl.kibernumacademy.model.Check;

public class CheckManager 
{   private final List<Check> checks = new ArrayList<>();

     public Check createCheck(String nombre, String tipo, String horario) { // Crear una reserva
        Check check = new Check(nombre, tipo, horario); // Invoca método constructor de reserva
        checks.add(check); // Agrega reserva a la lista
        return check;  // Retorna reserva para testing
    }

    public List<Check> getList() { // Obtener la lista
        return Collections.unmodifiableList(checks); // Retorna una lista 
    }

    public boolean updateCheck(String nombre, String newTipo, String newHorario) { // Actualizar  reserva
        try {
            Check check = getCheckByNombre(nombre); // Invoca la reserva desde el nombre
            if (newTipo != null && newHorario != null){ // Verifica nullidad de datos
                check.setTipo(newTipo);
                check.setHorario(newHorario); 
            }
            return true; // True == actualización  exitosa 
        } catch (IllegalArgumentException e) { // Manejo de error
            return false; // Ciclo fallido, check de aviso al test
        }
    }

    public boolean updateHorario(String nombre, String newHorario) { // Actualizar horario de la reserva
        try {   
            Check check = getCheckByNombre(nombre);  
            if (newHorario != null) {
                check.setHorario(newHorario);
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean updateTipo(String nombre, String newTipo) { // Actualizar tipo  de la reserva
            try {   
                Check check = getCheckByNombre(nombre);  
                if (newTipo != null) {
                    check.setTipo(newTipo);
                }
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }





    public boolean deleteCheck(String nombre) { // Eliminar reserva
        try {
            Check check = getCheckByNombre(nombre); // Captura reserva  desde el nombre
            checks.remove(check); // Elimina la reserva
            return true;
        } catch (IllegalArgumentException e) { // Manejo de error
            return false;
        }
    }

    private Check getCheckByNombre(String nombre) { // Encuentrar reserva con el nombre
        return checks.stream()
                .filter(t -> t.getNombre() == nombre)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada con nombre: " + nombre));
    }
}
