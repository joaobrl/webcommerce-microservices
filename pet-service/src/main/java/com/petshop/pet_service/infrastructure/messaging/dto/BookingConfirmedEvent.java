package com.petshop.pet_service.infrastructure.messaging.dto;

import java.time.LocalDateTime;
import java.util.UUID;


public record BookingConfirmedEvent(
        UUID bookingId,
        String ownerName,
        String ownerContact, // Will be used as the email address
        String petName,
        String serviceType,
        String employeeName,
        LocalDateTime bookingDateTime
) {}