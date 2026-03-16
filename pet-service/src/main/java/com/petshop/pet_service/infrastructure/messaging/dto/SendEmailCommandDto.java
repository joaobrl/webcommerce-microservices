package com.petshop.pet_service.infrastructure.messaging.dto;

public record SendEmailCommandDto(
        String to,
        String subject,
        String bodyHtml
) {}