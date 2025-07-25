package cl.kibernumacademy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import cl.kibernumacademy.model.Court;
import cl.kibernumacademy.model.Reservation;
import cl.kibernumacademy.service.CourtManager;
import cl.kibernumacademy.service.ReservationManager;


public class ReservationTest {

    private ReservationManager reservationManager;
    private CourtManager courtManager;


    @BeforeEach
    void setUp() {
        courtManager = new CourtManager(); 
        reservationManager = new ReservationManager();  

    }
 
    @Test
    void shouldCreateCourtSuccessfully() {
        
        String name = "Cancha A";
        String sportType = "Tenis";
        String schedule = "09:00";

        Court court = courtManager.createCourt(name, sportType, schedule);

        assertEquals("Cancha A", court.getName());
        assertEquals("Tenis", court.getSportType());
        assertEquals(Set.of(
            LocalTime.of(9, 0)
        ), court.getAvailableSchedules());

        List<Court> courts = courtManager.getList();
        assertEquals(1, courts.size());
        assertTrue(courts.contains(court));
    }

    @Test
    void shouldThrowExceptionWhenCourtNameAlreadyExists() {
        courtManager.createCourt("Cancha B", "Futbol", "08:00");
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> courtManager.createCourt("Cancha B", "Basketball", "09:00")
        );
        assertEquals("Ya existe una cancha con el nombre: Cancha B", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionOnInvalidScheduleFormat() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> courtManager.createCourt("Cancha C", "Padel", "08 am")
        );
        assertTrue(exception.getMessage().startsWith("Formato de hora inválido"));
    }

    @Test
    void shouldUpdateCourtSuccessfully() {
    courtManager.createCourt("Cancha D", "Volleyball", "10:00");
    boolean updated = courtManager.updateCourt("Cancha D", "Basketball", "10:00");
    assertTrue(updated);

    Court court = courtManager.getList().get(0);
    assertEquals("Basketball", court.getSportType());
    assertEquals(Set.of(
        LocalTime.of(10, 0)
    ), court.getAvailableSchedules());

}

 @Test
    void shouldCreateReservationSuccessfully() {
    Court court = courtManager.createCourt("Cancha E", "Futbol", "08:00");
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.of(8, 0);
    String user = "Ricardo";

    Reservation reservation = reservationManager.createReservation(court, date, time, user);

    assertEquals(court, reservation.getCourt());
    assertEquals(date, reservation.getDate());
    assertEquals(time, reservation.getTimeSlot());
    assertEquals(user, reservation.getUserName());

    List<Reservation> all = reservationManager.getList();
    assertEquals(1, all.size());
    assertTrue(all.contains(reservation));
    }

    @Test
    void shouldThrowExceptionIfTimeSlotNotAvailableInCourt() {
        Court court = courtManager.createCourt("Cancha F", "Tenis", "10:00"); 
        LocalDate date = LocalDate.now();
        LocalTime unavailableTime = LocalTime.of(9, 0); // 09:00 no permitido
         String user = "Laura";

        IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> reservationManager.createReservation(court, date, unavailableTime, user)
     );

    assertEquals("La cancha no está disponible en ese horario.", exception.getMessage());
      }

    @Test
    void shouldThrowExceptionIfCourtAlreadyReserved() {
        Court court = courtManager.createCourt("Cancha G", "Padel", "11:00");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(11, 0);

        reservationManager.createReservation(court, date, time, "Jose");

        IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> reservationManager.createReservation(court, date, time, "Andres")
    );

    assertEquals("La cancha ya está reservada en ese horario.", exception.getMessage());
    }

    @Test
    void shouldCountReservationsByDate() {
        Court court1 = courtManager.createCourt("Cancha H", "Basquetbol", "12:00");
        Court court2 = courtManager.createCourt("Cancha I", "Rugby", "12:00");
        LocalDate date = LocalDate.now();

        reservationManager.createReservation(court1, date, LocalTime.of(12, 0), "Ana");
        reservationManager.createReservation(court2, date, LocalTime.of(12, 0), "Pedro");

       long count = reservationManager.countReservationsByDay(date);
       assertEquals(2, count);
        
    }


}