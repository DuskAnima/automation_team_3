package cl.codewave.model;

import java.util.List;

import cl.codewave.interfaces.model.Court;

public class SportsCourt implements Court {
    private final String name;
    private final SportType sportType;
    private final List<TimeSlot> availableTimeSlots;

    public SportsCourt(String name, SportType sportType, List<TimeSlot> availableTimeSlots) {
        this.name = name;
        this.sportType = sportType;
        this.availableTimeSlots = availableTimeSlots;
    }

    @Override
    public String getName() { return name; }

    @Override
    public SportType getSportType() { return sportType; }

    @Override
    public List<TimeSlot> getAvailableTimeSlots() { return availableTimeSlots; }
}
