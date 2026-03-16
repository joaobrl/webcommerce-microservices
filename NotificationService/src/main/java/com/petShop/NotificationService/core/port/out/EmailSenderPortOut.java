package com.petShop.NotificationService.core.port.out;

import com.petShop.NotificationService.core.domain.EmailMessage;

public interface EmailSenderPortOut {
    void sendEmail(EmailMessage emailMessage);
}
