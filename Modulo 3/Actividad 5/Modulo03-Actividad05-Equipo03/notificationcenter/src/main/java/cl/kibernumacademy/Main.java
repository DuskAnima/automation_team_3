package cl.kibernumacademy;

import java.util.List;

import cl.kibernumacademy.model.NotificationSentHistory;
import cl.kibernumacademy.service.EmailNotification;
import cl.kibernumacademy.service.NotificationChannel;
import cl.kibernumacademy.service.NotificationProcessor;
import cl.kibernumacademy.service.SMSNotification;

public class Main {
    public static void main(String[] args) {
        NotificationChannel email = new EmailNotification();
        NotificationChannel sms = new SMSNotification();

        NotificationSentHistory history = new NotificationSentHistory();
        List<NotificationChannel> channels = List.of(email, sms);
        
        NotificationProcessor service = new NotificationProcessor(history, channels);
        service.notify();
    }

}
