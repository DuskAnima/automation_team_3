package cl.kibernumacademy.service;


public class SMSNotification implements NotificationChannel {

    @Override
    public boolean sent(String addressee,String channel,String message) {
        return true;
    }

    @Override
    public String getChannelName() {
        return "SMS";
    }

    
}
