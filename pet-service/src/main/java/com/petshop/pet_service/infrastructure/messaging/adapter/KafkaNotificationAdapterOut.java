package com.petshop.pet_service.infrastructure.messaging.adapter;

import com.petshop.pet_service.core.domain.PetBooking;
import com.petshop.pet_service.core.port.out.NotificationPortOut;
import com.petshop.pet_service.infrastructure.messaging.dto.BookingConfirmedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaNotificationAdapterOut implements NotificationPortOut {

    private static final String TOPIC_NAME = "booking.confirmed.topic";

    private final KafkaTemplate<String, BookingConfirmedEvent> kafkaTemplate;

    @Override
    public void sendBookingConfirmation(PetBooking booking) {

        var event = new BookingConfirmedEvent(
                booking.getId(),
                booking.getPet().getOwnerName(),
                booking.getPet().getOwnerContact(),
                booking.getPet().getPetName(),
                booking.getServiceDetails().getServiceType().name(),
                booking.getEmployeeName(),
                booking.getBookingDateTime()
        );

        try {
            String messageKey = booking.getId().toString();

            kafkaTemplate.send(TOPIC_NAME, messageKey, event);

            log.info("Successfully published BookingConfirmedEvent to Kafka Topic [{}] for Booking ID: {}",
                    TOPIC_NAME, booking.getId());

        } catch (Exception e) {
            log.error("Failed to publish BookingConfirmedEvent to Kafka", e);
        }
    }
}