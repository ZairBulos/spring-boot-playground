package com.zair.business.services.impl;

import com.zair.business.services.MailService;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Override
    public void send(String to, String subject, String text) {
        System.out.println("Sending email to: " + to + " | Subject: " + subject + " | Message: " + text);
    }
}
