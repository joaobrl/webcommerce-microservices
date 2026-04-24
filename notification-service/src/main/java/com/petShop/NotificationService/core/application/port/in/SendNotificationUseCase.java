package com.petShop.NotificationService.core.application.port.in;

import com.petShop.NotificationService.adapter.in.kafka.dto.SendEmailCommandDto;

public interface SendNotificationUseCase {
    void execute(SendEmailCommandDto command);
}