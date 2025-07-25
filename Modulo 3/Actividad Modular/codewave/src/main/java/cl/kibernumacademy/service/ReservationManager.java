package cl.kibernumacademy.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.kibernumacademy.model.Court;
import cl.kibernumacademy.model.Reservation;

public class ReservationManager {
    private final List<Reservation> reservations = new ArrayList<>();

    public Reservation createReservation(Court court, LocalDate date, LocalTime timeSlot, String userName) {
        if (!court.getAvailableSchedules().contains(timeSlot)) {
            throw new IllegalArgumentException("La cancha no está disponible en ese horario.");
        }

        if (isCourtOccupied(court, date, timeSlot)) {
            throw new IllegalArgumentException("La cancha ya está reservada en ese horario.");
        }

        Reservation reservation = new Reservation(court, date, timeSlot, userName);
        reservations.add(reservation);
        return reservation;
    }

    public boolean isCourtOccupied(Court court, LocalDate date, LocalTime timeSlot) {
        return reservations.stream().anyMatch(r ->
            r.getCourt().getName().equalsIgnoreCase(court.getName()) &&
            r.getDate().equals(date) &&
            r.getTimeSlot().equals(timeSlot)
        );
    }

    public long countReservationsByDate(LocalDate date) {
        return reservations.stream()
                .filter(r -> r.getDate().equals(date))
                .count();
    }

    public List<Reservation> getList() { 
        return Collections.unmodifiableList(reservations);
    }

    public long countReservationsByDay(LocalDate date) {
    return reservations.stream()
        .filter(r -> r.getDate().equals(date))
        .count();
    }

    public boolean cancelReservation(Reservation reservation) {
    return reservations.remove(reservation);
}




}
