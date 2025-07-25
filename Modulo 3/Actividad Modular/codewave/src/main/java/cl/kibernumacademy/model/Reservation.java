package cl.kibernumacademy.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
     private Court court;
    private LocalDate date;
    private LocalTime timeSlot;
    private String userName;

    public Reservation(Court court, LocalDate date, LocalTime timeSlot, String userName) {
        this.court = court;
        this.date = date;
        this.timeSlot = timeSlot;
        this.userName = userName;
    }

    public Court getCourt() {
        return court;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTimeSlot() {
        return timeSlot;
    }

    public String getUserName() {
        return userName;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTimeSlot(LocalTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
