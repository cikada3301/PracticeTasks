package com.example.emailservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @KafkaListener(topics = "email_details", groupId = "1")
    public void sendCode(String email) {
        System.out.println("Your verification code: " + email);
    }
}
