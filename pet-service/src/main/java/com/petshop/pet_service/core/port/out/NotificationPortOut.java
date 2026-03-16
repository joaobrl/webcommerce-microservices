package com.petshop.pet_service.core.port.out;

import com.petshop.pet_service.core.domain.PetBooking;

public interface NotificationPortOut {
    void sendBookingScheduled(PetBooking booking);
    void sendBookingCompleted(PetBooking booking);
    void sendBookingCanceled(PetBooking booking);
}
