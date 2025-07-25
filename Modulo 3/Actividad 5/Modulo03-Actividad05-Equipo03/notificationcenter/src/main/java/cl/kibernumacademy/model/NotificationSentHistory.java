package cl.kibernumacademy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationSentHistory {
    private final List<NotificationSent> notifications = new ArrayList<>();

    public void add(NotificationSent notificationSent) {
        notifications.add(notificationSent);
    }

    public List<NotificationSent> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }
}
