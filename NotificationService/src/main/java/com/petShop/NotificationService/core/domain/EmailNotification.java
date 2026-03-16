package com.petShop.NotificationService.core.domain;

public record EmailNotification(String recipient, String subject, String content) {

    public EmailNotification {
        if (recipient == null || recipient.isBlank()) {
            throw new IllegalArgumentException("Recipient email address cannot be null or blank");
        }
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Email subject cannot be null or blank");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Email content cannot be null or blank");
        }
    }
}