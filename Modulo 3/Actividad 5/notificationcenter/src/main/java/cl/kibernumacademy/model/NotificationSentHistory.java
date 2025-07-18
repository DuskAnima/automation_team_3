package cl.kibernumacademy.model;

import java.util.ArrayList;
import java.util.List;

public class NotificationSentHistory {
     private final List<NotificationSent> history = new ArrayList<>();

    public void add(NotificationSent notificationSent) {
        history.add(notificationSent);
    }

    public List<NotificationSent> getAll() {
        return history;
    }
}

