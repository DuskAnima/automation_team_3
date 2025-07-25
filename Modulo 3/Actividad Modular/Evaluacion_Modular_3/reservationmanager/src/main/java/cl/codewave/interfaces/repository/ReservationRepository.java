package cl.codewave.interfaces.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import cl.codewave.interfaces.model.Court;
import cl.codewave.interfaces.model.Reservation;

// Interrfaz de la base de datos de reservas
public interface ReservationRepository {
    void save(Reservation reservation);
    void delete(String reservationId);
    Optional<Reservation> findById(String reservationId);
    List<Reservation> findByDate(LocalDate date);
    List<Reservation> findByCourtAndDate(Court court, LocalDate date);
}