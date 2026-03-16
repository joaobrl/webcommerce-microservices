package com.petShop.NotificationService.core.port.in;

public interface SendNotificationUseCase {
    void sendBookingConfirmation(SendBookingConfirmationCommand command);
}
