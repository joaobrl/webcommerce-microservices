package com.petShop.NotificationService.core.domain;

import lombok.Getter;

@Getter
public class EmailMessage {
    private final String recipient;
    private final String subject;
    private final String body;

    public EmailMessage(String recipient, String subject, String body) {
        if (recipient == null || recipient.isBlank()) {
            throw new IllegalArgumentException("Recipient cannot be null or blank");
        }
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Subject cannot be null or blank");
        }
        if (body == null || body.isBlank()) {
            throw new IllegalArgumentException("Body cannot be null or blank");
        }

        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }
}
