package com.petshop.pet_service.infrastructure.persistence.postgresql.adapter;

import com.petshop.pet_service.core.domain.PetBooking;
import com.petshop.pet_service.core.port.in.dto.BookingSearchCriteriaDto;
import com.petshop.pet_service.core.port.out.BookingPortOut;
import com.petshop.pet_service.infrastructure.persistence.postgresql.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookingPersistenceAdapterOut implements BookingPortOut {

    private final BookingRepository bookingRepository;

    @Override
    public PetBooking save(PetBooking booking) {
        // In a strictly segregated architecture, object mapping would occur here:
        // PetBookingEntity entity = mapper.toEntity(booking);
        // PetBookingEntity savedEntity = bookingRepository.save(entity);
        // return mapper.toDomain(savedEntity);

        return bookingRepository.save(booking);
    }

    @Override
    public List<PetBooking> findByCriteria(BookingSearchCriteriaDto criteria) {
        return bookingRepository.findByCriteria(criteria);
    }

    @Override
    public Optional<PetBooking> findById(UUID id) {
        return bookingRepository.findById(id);
    }
}