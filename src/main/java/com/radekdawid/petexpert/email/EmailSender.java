package com.radekdawid.petexpert.email;

public interface EmailSender {

    void send(String to, String email, String subject);
}
