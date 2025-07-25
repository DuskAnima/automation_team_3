package cl.codewave.interfaces.service;

import java.time.LocalDate;

import cl.codewave.model.TimeSlot;

// Interfaz del servicio de reservas
public interface ReservationService {
    void createReservation(String courtName, LocalDate date, TimeSlot slot);
    void modifyReservation(String reservationId, LocalDate newDate, TimeSlot newSlot);
    void cancelReservation(String reservationId);
    int countReservationsByDate(LocalDate date);
}