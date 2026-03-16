package com.petShop.NotificationService.core.port.in;

import java.time.LocalDateTime;

public record SendBookingConfirmationCommand(
        String ownerName,
        String ownerContact,
        String petName,
        String serviceType,
        String employeeName,
        LocalDateTime bookingDateTime
) {}
