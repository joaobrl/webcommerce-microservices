package com.petShop.NotificationService.adapter.in.kafka.dto;

public record SendEmailCommandDto(
        String to,
        String subject,
        String bodyHtml
) {}
