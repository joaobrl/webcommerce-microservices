package com.petShop.NotificationService.core.application.service;

import com.petShop.NotificationService.adapter.in.kafka.dto.SendEmailCommandDto;
import com.petShop.NotificationService.core.application.port.in.SendNotificationUseCase;
import com.petShop.NotificationService.core.application.port.out.EmailSenderPortOut;
import com.petShop.NotificationService.core.domain.EmailNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements SendNotificationUseCase {

    private final EmailSenderPortOut emailSenderPortOut;

    @Override
    public void execute(SendEmailCommandDto command) {
        var emailNotification = new EmailNotification(
                command.to(),
                command.subject(),
                command.bodyHtml()
        );

        emailSenderPortOut.send(emailNotification);
    }
}