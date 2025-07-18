package cl.kibernumacademy.service;

public interface NotificationChannel {

    boolean sent(String adressee, String message, String channel);
    String getChannelName();
}
