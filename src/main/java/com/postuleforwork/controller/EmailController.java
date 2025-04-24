package com.postuleforwork.controller;

import com.postuleforwork.dto.EmailRequest;
import com.postuleforwork.service.EmailService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class EmailController {
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setSubject("Candidature – Développeur Full Stack Spring Boot / Angular");
        model.addAttribute("emailRequest", emailRequest);
        return "send-cv";
    }

    @PostMapping("/send-cv")
    public String sendCv(
            @Valid @ModelAttribute("emailRequest") EmailRequest emailRequest,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            logger.warn("Erreur de validation du formulaire");
            return "send-cv";
        }

        try {
            emailService.sendCvEmail(emailRequest);
            redirectAttributes.addFlashAttribute("successMessage",
                    "CV envoyé avec succès à " + emailRequest.getRecipientEmail());
        } catch (Exception e) {
            logger.error("Erreur lors de l'envoi du CV", e);
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Erreur lors de l'envoi du CV: " + e.getMessage());
        }

        return "redirect:/";
    }
}