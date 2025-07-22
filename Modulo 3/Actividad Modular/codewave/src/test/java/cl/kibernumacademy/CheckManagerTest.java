package cl.kibernumacademy;

//import static org.junit.Assert.assertTrue;

//import org.junit.Test;

//import static org.junit.jupiter.api.Assertions.assertTrue;
 
/*import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
*/

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;



import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.junit.jupiter.api.Test;

import cl.kibernumacademy.service.CheckManager;
import cl.kibernumacademy.model.Check;



 
public class CheckManagerTest 
{
    private CheckManager manager; // Se crea el servicio

    @BeforeEach
    void setUp() {
        manager = new CheckManager();
    }
 

 @Test // Pruebas de creación de reserva
    void shouldCreateCheck() {
        Check  check = manager.createCheck("nombre reserva", "tipo de reserva", "horario de reserva"); // Modelo
        assertThat(check.getNombre()).isEqualTo("nombre reserva"); // La reserva fue creada?
        assertThat(manager.getList()).hasSize(1);  // La reserva fue agregada a la lista?
    }




    @Test // Prueba de edición de reserva
    void shouldUpdateCheck() {
        Check check = manager.createCheck("nombre reserva", "tipo de reserva", "horario de reserva");  // Modelo 
        boolean updateTipo = manager.updateTipo(check.getNombre(), "Nuevo Tipo");
        boolean updateHorario = manager.updateHorario(check.getNombre(), "Nuevo Horario"); // Se altera horario
        assertThat(updateTipo).isTrue(); // El tipo fue actualizado
        assertThat(updateHorario).isTrue(); // El Horario fue actualizado
        assertThat(check.getTipo()).isEqualTo("Nuevo Tipo"); // Devuelve tipo
        assertThat(check.getHorario()).isEqualTo("Nuevo Horario"); // Devuelve horario
    }

    @Test // Prueba de eliminación de reserva
    void shouldDeleteCheck() {
        Check check = manager.createCheck("nombre reserva", "tipo de reserva", "horario de reserva"); // Modelo
        boolean removed = manager.deleteCheck(check.getNombre()); // Se elimina reserva  de la lista
        assertThat(removed).isTrue(); // La reserva  fue eliminada? 
        assertThat(manager.getList()).isEmpty(); // La lista está vacía?
    }
}
