package uz.tuit.appquiz.service;


public interface EmailService {

    void mailSender(String to, String subject, String content);
}
