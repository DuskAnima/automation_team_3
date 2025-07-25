package cl.codewave.interfaces.model;

import java.time.LocalDate;

import cl.codewave.model.TimeSlot;

// Interfaz del modelo de reserva
public interface Reservation {
    String getId();
    Court getCourt();
    LocalDate getDate();
    TimeSlot getTimeSlot();
}