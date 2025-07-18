package cl.kibernumacademy.model;

public class NotificationSent {
    private final String addressee;
    private final String message;
    private final String channel;
    private final java.time.LocalDateTime date;

    public NotificationSent( String addressee, String channel, String message, java.time.LocalDateTime date) {
        this.addressee = addressee;
        this.message = message;
        this.channel = channel;
        this.date = date;
    }

    public String addressee() {return addressee;}
    public String message() {return message;}
    public String channel() {return channel;}
    public java.time.LocalDateTime getDate() { return date; }

}
