package com.petshop.pet_service.core.port.out;

import com.petshop.pet_service.core.domain.PetBooking;
import com.petshop.pet_service.core.port.in.dto.BookingSearchCriteriaDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingPortOut {
    PetBooking save(PetBooking booking);
    List<PetBooking> findByCriteria(BookingSearchCriteriaDto criteria);
    Optional<PetBooking> findById(UUID id);
}