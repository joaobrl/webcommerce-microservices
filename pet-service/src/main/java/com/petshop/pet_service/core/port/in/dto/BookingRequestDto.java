package com.petshop.pet_service.core.port.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petshop.pet_service.core.domain.enums.ServiceType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequestDto {

    @Valid
    @NotNull
    private PetRequestDto pet;

    @NotNull
    private ServiceType serviceType;

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime bookingDateTime;

    private String observations;

}
