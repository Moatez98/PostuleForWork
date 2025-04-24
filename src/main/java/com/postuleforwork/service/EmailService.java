package com.postuleforwork.service;

import com.postuleforwork.dto.EmailRequest;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendCvEmail(EmailRequest emailRequest) throws MessagingException, UnsupportedEncodingException;
}
