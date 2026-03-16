package com.petshop.pet_service.infrastructure.messaging.adapter;

import com.petshop.pet_service.core.domain.PetBooking;
import com.petshop.pet_service.core.port.out.NotificationPortOut;
import com.petshop.pet_service.infrastructure.messaging.dto.SendEmailCommandDto;
import com.petshop.pet_service.infrastructure.messaging.adapter.template.BookingEmailTemplateEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaNotificationAdapterOut implements NotificationPortOut {

    private static final String TOPIC_COMMANDS = "notification.commands";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final BookingEmailTemplateEngine templateEngine;

    @Override
    public void sendBookingScheduled(PetBooking booking) {
        String subject = "Agendamento Confirmado: " + booking.getPet().getPetName();
        String htmlBody = templateEngine.buildScheduledEmail(booking);

        var command = new SendEmailCommandDto(booking.getPet().getOwnerContact(), subject, htmlBody);
        publishCommand(booking.getId().toString(), command);
    }

    @Override
    public void sendBookingCompleted(PetBooking booking) {
        String subject = "Serviço Finalizado: Venha buscar o " + booking.getPet().getPetName();
        String htmlBody = templateEngine.buildCompletedEmail(booking);

        var command = new SendEmailCommandDto(booking.getPet().getOwnerContact(), subject, htmlBody);
        publishCommand(booking.getId().toString(), command);
    }

    @Override
    public void sendBookingCanceled(PetBooking booking) {
        String subject = "Agendamento Cancelado: " + booking.getPet().getPetName();
        String htmlBody = templateEngine.buildCanceledEmail(booking);

        var command = new SendEmailCommandDto(booking.getPet().getOwnerContact(), subject, htmlBody);
        publishCommand(booking.getId().toString(), command);
    }

    private void publishCommand(String partitionKey, SendEmailCommandDto command) {
        try {
            kafkaTemplate.send(TOPIC_COMMANDS, partitionKey, command);
            log.info("Successfully published email command to Topic [{}] for Booking ID: {}", TOPIC_COMMANDS, partitionKey);
        } catch (Exception e) {
            log.error("Failed to publish email command to Topic [{}]", TOPIC_COMMANDS, e);
        }
    }
}