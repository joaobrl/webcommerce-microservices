package com.petshop.pet_service.core.port.out.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petshop.pet_service.core.domain.PetBooking;
import com.petshop.pet_service.core.domain.enums.StatusBooking;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingResponseDto {

    private UUID id;

    private PetResponseDto pet;

    private ServiceDetailsResponseDto serviceType;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime bookingDateTime;

    private StatusBooking status;

    private String observations;

    public BookingResponseDto(PetBooking booking) {
        this.id = booking.getId();

        this.pet = new PetResponseDto();
        this.pet.setPetName(booking.getPet().getPetName());
        this.pet.setPetType(booking.getPet().getPetType());
        this.pet.setPetBreed(booking.getPet().getPetBreed());
        this.pet.setPetSize(booking.getPet().getPetSize());
        this.pet.setWeightInKg(booking.getPet().getWeightInKg());
        this.pet.setPetHealthIssues(booking.getPet().getPetHealthIssues());
        this.pet.setOwnerName(booking.getPet().getOwnerName());

        this.serviceType = new ServiceDetailsResponseDto();
        this.serviceType.setServiceType(booking.getServiceDetails().getServiceType());
        this.serviceType.setPrice(booking.getServiceDetails().getPrice());
        this.serviceType.setDurationInMinutes(booking.getServiceDetails().getDurationInMinutes());

        this.status = booking.getStatus();
        this.bookingDateTime = booking.getBookingDateTime();
        this.observations = booking.getObservations();
    }
}
