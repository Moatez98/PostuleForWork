package com.postuleforwork.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {
    @Email(message = "L'email doit être valide")
    @NotEmpty(message = "L'email ne peut pas être vide")
    private String recipientEmail;

    @NotEmpty(message = "L'objet ne peut pas être vide")
    private String subject;

}