package com.petshop.pet_service.core.port.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petshop.pet_service.core.domain.enums.ServiceType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingUpdateDto {

    private PetRequestDto pet;

    private ServiceType serviceType;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime bookingDateTime;

    private String observations;
}
