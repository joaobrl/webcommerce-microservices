package com.petShop.NotificationService.core.application.port.out;

import com.petShop.NotificationService.core.domain.EmailNotification;

public interface EmailSenderPortOut {
    void send(EmailNotification emailNotification);
}