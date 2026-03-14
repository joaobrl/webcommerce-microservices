package com.petshop.pet_service.core.port.out;

import com.petshop.pet_service.core.domain.PetBooking;

public interface NotificationPortOut {

    void sendBookingConfirmation(PetBooking booking);

}
