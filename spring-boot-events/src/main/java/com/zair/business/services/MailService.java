package com.zair.business.services;

public interface MailService {
    void send(String to, String subject, String text);
}
