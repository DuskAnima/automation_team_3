package cl.kibernumacademy.service;

import java.time.LocalDateTime;

import cl.kibernumacademy.model.NotificationSent;
import cl.kibernumacademy.model.NotificationSentHistory;

public class NotificationProcessor {

    private final NotificationSentHistory notificationSentHistory;
    private final NotificationChannel emailNotification;
    private final NotificationChannel smsNotification;

    public NotificationProcessor(NotificationSentHistory notificationSentHistory, NotificationChannel emailNotification, NotificationChannel smsNotification) {
        this.notificationSentHistory = notificationSentHistory;
        this.emailNotification = emailNotification;
        this.smsNotification = smsNotification;
    }

     public boolean processNotification(String addressee, String channel, String message) {
        if (message.isBlank() || addressee == null) {
            throw new IllegalArgumentException("Destinatario o mensaje invalido");
        }
        boolean result;
        if ("SMS".equalsIgnoreCase(channel)) {
            result = smsNotification.sent(addressee, channel, message); // Aquí se invoca el mock en los tests
        } else if ("Email".equalsIgnoreCase(channel)) {
            result = emailNotification.sent(addressee, channel, message); // Aquí se invoca el mock en los tests
        } else {
            throw new IllegalArgumentException("Unknown channel notification");
        }
        if (result) {
            notificationSentHistory.add(new NotificationSent(addressee, channel, message, LocalDateTime.now()));
        }
        return result;
    }

    public NotificationSentHistory getNotificationSentHistory() {
        return notificationSentHistory;
    }
    
}