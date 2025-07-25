package cl.kibernumacademy.service;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cl.kibernumacademy.model.Court;

public class CourtManager {   
    private final List<Court> courts = new ArrayList<>();

     public Court createCourt(String name, String sportType, String schedule) { // Crear una reserva
        if (courts.stream().anyMatch(c -> c.getName().equalsIgnoreCase(name))) {
            throw new IllegalArgumentException("Ya existe una cancha con el nombre: " + name);
        }
        Set<LocalTime> schedules = parseSchedule(schedule);
        Court court = new Court(name, sportType,schedules ); // Invoca método constructor de reserva
        courts.add(court); // Agrega reserva a la lista
        return court;  // Retorna reserva para testing
    }

    public List<Court> getList() { // Obtener la lista
        return Collections.unmodifiableList(courts); // Retorna una lista 
    }

    public boolean updateCourt(String name, String sportType, String schedule) { // Actualizar  reserva
        Court court = getCourtByName(name);
        court.setSportType(sportType);
        Set<LocalTime> newSchedules = parseSchedule(schedule);
        court.setAvailableSchedules(newSchedules);
        return true;
    }

    public boolean updateSchedule(String name, String schedule) { // Actualizar horario de la reserva
        Court court = getCourtByName(name);
        Set<LocalTime> newSchedules = parseSchedule(schedule);
        court.setAvailableSchedules(newSchedules);
        return true;
    }

    public boolean updateSportType(String name, String sportType) { // Actualizar tipo  de la reserva
        Court court = getCourtByName(name);
        court.setSportType(sportType);
        return true;
      }


    public boolean deleteCourt(String name) { // Eliminar reserva
        Court court = getCourtByName(name);
        return courts.remove(court);
    }

    private Court getCourtByName(String name) { // Encuentrar reserva con el nombre
        return courts.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada con nombre: " + name));
    }

    private Set<LocalTime> parseSchedule(String schedule) {
        Set<LocalTime> schedules = new HashSet<>();
        for (String time : schedule.split(",")) {
            try {
                schedules.add(LocalTime.parse(time.trim()));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Formato de hora inválido: " + time.trim());
            }
        }
        return schedules;
    }
}
