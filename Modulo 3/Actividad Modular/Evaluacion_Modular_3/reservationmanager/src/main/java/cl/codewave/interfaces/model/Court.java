package cl.codewave.interfaces.model;

import java.util.List;

import cl.codewave.model.SportType;
import cl.codewave.model.TimeSlot;

// Interfaz del modelo de cancha
public interface Court {
    String getName();
    SportType getSportType();
    List<TimeSlot> getAvailableTimeSlots();
}
