package com.petshop.pet_service.core.domain;

import com.petshop.pet_service.core.domain.enums.PetType;
import com.petshop.pet_service.core.domain.enums.SizeCategory;
import com.petshop.pet_service.core.domain.enums.StatusBooking;
import com.petshop.pet_service.core.port.in.dto.BookingRequestDto;
import com.petshop.pet_service.core.port.in.dto.BookingUpdateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pet_bookings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PetBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private Pet pet;

    @Embedded
    private ServiceDetails serviceDetails;

    @Column(length = 500)
    private String observations;

    @Column(nullable = false)

    private LocalDateTime bookingDateTime;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private StatusBooking status;

    private String employeeName;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public PetBooking(BookingRequestDto bookingRequest) {
        this.pet = new Pet(bookingRequest.getPet().getPetName(),
                bookingRequest.getPet().getPetType(),
                bookingRequest.getPet().getPetBreed(),
                bookingRequest.getPet().getPetSize(),
                bookingRequest.getPet().getWeightInKg(),
                bookingRequest.getPet().getPetHealthIssues(),
                bookingRequest.getPet().getOwnerName(),
                bookingRequest.getPet().getOwnerCpf(),
                bookingRequest.getPet().getOwnerContact());
        this.serviceDetails = new ServiceDetails(bookingRequest.getServiceType());
        this.observations = bookingRequest.getObservations();
        this.bookingDateTime = bookingRequest.getBookingDateTime();
        this.status = StatusBooking.SCHEDULED;
    }

    public void Update(BookingUpdateDto bookingUpdate) {

        if (bookingUpdate.getPet() != null) {
            var incomingPet = bookingUpdate.getPet();

            String updatedName = incomingPet.getPetName() != null ? incomingPet.getPetName() : this.pet.getPetName();
            PetType updatedType = incomingPet.getPetType() != null ? incomingPet.getPetType() : this.pet.getPetType();
            String updatedBreed = incomingPet.getPetBreed() != null ? incomingPet.getPetBreed() : this.pet.getPetBreed();
            SizeCategory updatedSize = incomingPet.getPetSize() != null ? incomingPet.getPetSize() : this.pet.getPetSize();
            Double updatedWeight = incomingPet.getWeightInKg() != null ? incomingPet.getWeightInKg() : this.pet.getWeightInKg();
            String updatedHealth = incomingPet.getPetHealthIssues() != null ? incomingPet.getPetHealthIssues() : this.pet.getPetHealthIssues();
            String updatedOwnerName = incomingPet.getOwnerName() != null ? incomingPet.getOwnerName() : this.pet.getOwnerName();
            String updatedOwnerCpf = incomingPet.getOwnerCpf() != null ? incomingPet.getOwnerCpf() : this.pet.getOwnerCpf();
            String updatedOwnerContact = incomingPet.getOwnerContact() != null ? incomingPet.getOwnerContact() : this.pet.getOwnerContact();

            this.pet = new Pet(updatedName, updatedType, updatedBreed, updatedSize, updatedWeight, updatedHealth, updatedOwnerName, updatedOwnerCpf, updatedOwnerContact);
        }

        if (bookingUpdate.getServiceType() != null) {
            this.serviceDetails = new ServiceDetails(bookingUpdate.getServiceType());
        }
        if (bookingUpdate.getObservations() != null) {
            this.observations = bookingUpdate.getObservations();
        }
        if (bookingUpdate.getBookingDateTime() != null) {
            this.bookingDateTime = bookingUpdate.getBookingDateTime();
        }
    }

    public void completeBooking(String employeeName) {
        if (this.status != StatusBooking.SCHEDULED) {
            throw new IllegalStateException("Only scheduled bookings can be finalized.");
        }
        this.employeeName = employeeName;
        this.status = StatusBooking.COMPLETED;
    }
}
