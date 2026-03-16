package com.petShop.NotificationService.adapter.in.kafka;

import com.petShop.NotificationService.adapter.in.kafka.dto.SendEmailCommandDto;
import com.petShop.NotificationService.core.application.port.in.SendNotificationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationCommandConsumer {

    private final SendNotificationUseCase sendNotificationUseCase;

    @KafkaListener(topics = "notification.commands", groupId = "notification-group")
    public void consume(SendEmailCommandDto command) {
        log.info("Received command to send email to: {}", command.to());
        try {
            sendNotificationUseCase.execute(command);
            log.info("Successfully processed email command for: {}", command.to());
        } catch (Exception e) {
            log.error("Failed to process email command for: {}", command.to(), e);
        }
    }
}