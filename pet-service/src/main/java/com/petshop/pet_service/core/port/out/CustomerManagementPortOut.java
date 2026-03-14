package com.petshop.pet_service.core.port.out;

import java.time.LocalDateTime;

public interface CustomerManagementPortOut {

    int getAvailableSlots(LocalDateTime dateTime);

}
