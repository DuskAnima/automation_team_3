package cl.kibernumacademy.model;

import java.time.LocalTime;
import java.util.Set;

public class Court {
    private String name;
    private String sportType;
    private Set<LocalTime> availableSchedules;

    public Court (String name, String sportType, Set<LocalTime> availableSchedules) {
        this.name = name;
        this.sportType = sportType;
        this.availableSchedules = availableSchedules;
    }

    public String getName() { return name; }
    public String getSportType() { return sportType; }
    public Set<LocalTime> getAvailableSchedules() { return availableSchedules; }

    public void setName(String name) {
        this.name = name;
    }

    public void setSportType(String sportString) {
        this.sportType = sportString;
    }

    public void setAvailableSchedules(Set<LocalTime> availableSchedules) {
    this.availableSchedules = availableSchedules;
}

}
