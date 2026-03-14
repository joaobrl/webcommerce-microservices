package com.petshop.pet_service.core.port.in.dto;

import com.petshop.pet_service.core.domain.enums.ServiceType;
import com.petshop.pet_service.core.domain.enums.StatusBooking;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingSearchCriteriaDto {
    private String ownerCpf;
    private String petName;
    private LocalDate date;
    private ServiceType serviceType;
    private String employeeName;
    private StatusBooking status;

}
