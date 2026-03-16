package com.petShop.NotificationService.infrastructure.messaging.kafka.consumer;

import com.petShop.NotificationService.core.port.in.SendBookingConfirmationCommand;
import com.petShop.NotificationService.core.port.in.SendNotificationUseCase;
import com.petShop.NotificationService.infrastructure.messaging.kafka.dto.BookingConfirmedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookingEventConsumer {

    private final SendNotificationUseCase sendNotificationUseCase;

    @KafkaListener(topics = "booking.confirmed.topic", groupId = "notification-group")
    public void consumeBookingConfirmedEvent(BookingConfirmedEvent event) {
        log.info("Received Kafka event: Service completed for Pet [{}]", event.petName());

        var command = new SendBookingConfirmationCommand(
                event.ownerName(),
                event.ownerContact(),
                event.petName(),
                event.serviceType(),
                event.employeeName(),
                event.bookingDateTime()
        );

        sendNotificationUseCase.sendBookingConfirmation(command);

        log.info("Notification process finished successfully for Booking ID: {}", event.bookingId());
    }
}
