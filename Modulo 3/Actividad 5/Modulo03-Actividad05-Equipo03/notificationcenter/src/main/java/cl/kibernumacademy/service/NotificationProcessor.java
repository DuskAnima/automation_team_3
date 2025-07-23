package cl.kibernumacademy.service;

import java.time.LocalDateTime;
import java.util.List;

import cl.kibernumacademy.model.NotificationSent;
import cl.kibernumacademy.model.NotificationSentHistory;

public class NotificationProcessor {
    private final NotificationSentHistory notificationSentHistory;
    private final List<NotificationChannel> channels;

    public NotificationProcessor(NotificationSentHistory notificationSentHistory, List<NotificationChannel> channels) {
        this.notificationSentHistory = notificationSentHistory;
        this.channels = channels != null ? channels : List.of();
    }

    public boolean processNotification(String addressee, String channel, String message) {
        // Validación de inputs
        if (addressee == null || message == null || message.isBlank()) {
            throw new IllegalArgumentException("Destinatario o mensaje invalido");
        }
        
        // Validación temprana de canal desconocido (sin tocar mocks)
        if (!"SMS".equalsIgnoreCase(channel) && !"Email".equalsIgnoreCase(channel)) {
            throw new IllegalArgumentException("Unknown channel notification");
        }
        
        // Selección del canal usando el nombre (aquí se invocan los mocks solo en éxitos)
        NotificationChannel selectedChannel = channels.stream()
            .filter(ch -> ch.getChannelName().equalsIgnoreCase(channel))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unknown channel notification"));

        boolean result = selectedChannel.sent(addressee, channel, message);

        if (result) {
            notificationSentHistory.add(new NotificationSent(addressee, channel, message, LocalDateTime.now()));
        }

        return result;
    }

    public NotificationSentHistory getNotificationSentHistory() {
        return notificationSentHistory;
    }
}
