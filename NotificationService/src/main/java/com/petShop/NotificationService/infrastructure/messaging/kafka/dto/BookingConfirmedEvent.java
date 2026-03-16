package com.petShop.NotificationService.infrastructure.messaging.kafka.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingConfirmedEvent(
        UUID bookingId,
        String ownerName,
        String ownerContact,
        String petName,
        String serviceType,
        String employeeName,
        LocalDateTime bookingDateTime
) {}