package cl.kibernumacademy.service;


public class EmailNotification implements NotificationChannel {
    
    @Override
    public boolean sent(String adressee,String channel,String message) {
        return true;
    }

    @Override
    public String getChannelName() {
        return "Email";
    }
}
