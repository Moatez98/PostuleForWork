package com.postuleforwork.service.impl;

import com.postuleforwork.dto.EmailRequest;
import com.postuleforwork.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private static final String DEFAULT_SUBJECT = "Candidature – Développeur Full Stack Spring Boot / Angular";
    private static final String CV_FILENAME = "Rehouma-Moatez.pdf";
    private static final String CV_PATH = "static/pdf/" + CV_FILENAME;

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public void sendCvEmail(EmailRequest emailRequest)
            throws MessagingException, UnsupportedEncodingException {

        logger.info("Tentative d'envoi de CV à: {}", emailRequest.getRecipientEmail());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Préparation du contenu HTML
        Context context = new Context();
        String htmlContent = templateEngine.process("email-template", context);

        // Configuration de l'email
        helper.setFrom("reh.moatez@gmail.com", "Rehouma Moatez");
        helper.setTo(emailRequest.getRecipientEmail());
        helper.setSubject(emailRequest.getSubject() != null ? emailRequest.getSubject() : DEFAULT_SUBJECT);
        helper.setText(htmlContent, true);

        // Ajout du CV en pièce jointe
        ClassPathResource pdf = new ClassPathResource(CV_PATH);
        helper.addAttachment(CV_FILENAME, pdf);

        mailSender.send(message);
        logger.info("Email envoyé avec succès à {}", emailRequest.getRecipientEmail());
    }
}
