package cl.kibernumacademy.service;

public interface NotificationChannel {

    boolean sent(String addressee, String channel, String message);
    String getChannelName();
}
