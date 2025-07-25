package cl.codewave.model;

import java.time.LocalTime;

public class TimeSlot {
    private final LocalTime start;
    private final LocalTime end;

    public TimeSlot(LocalTime start, LocalTime end) {
        if (start.isAfter(end)) throw new IllegalArgumentException("Start debería definirse antes de End");
        this.start = start;
        this.end = end;
    }

    public boolean overlaps(TimeSlot other) {
        return !(this.end.isBefore(other.start) || this.start.isAfter(other.end));
    }

    public LocalTime getStart() { return start; }
    public LocalTime getEnd() { return end; }
}
