package id.co.mii.overtimeserverapp.repositories;

public interface EmailSender {
    void send(String to, String email);
}