package cl.codewave.model;

import java.time.LocalDate;

import cl.codewave.interfaces.model.Court;
import cl.codewave.interfaces.model.Reservation;

public class CourtReservation implements Reservation {
    private final String id;
    private final Court court;
    private final LocalDate date;
    private final TimeSlot slot;

    public CourtReservation(String id, Court court, LocalDate date, TimeSlot slot) {
        this.id = id;
        this.court = court;
        this.date = date;
        this.slot = slot;
    }

    @Override
    public String getId() { return id; }

    @Override
    public Court getCourt() { return court; }

    @Override
    public LocalDate getDate() { return date; }

    @Override
    public TimeSlot getTimeSlot() { return slot; }
}
