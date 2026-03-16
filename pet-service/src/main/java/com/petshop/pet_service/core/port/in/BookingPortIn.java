package com.petshop.pet_service.core.port.in;

import com.petshop.pet_service.core.domain.PetBooking;
import com.petshop.pet_service.core.port.in.dto.BookingRequestDto;
import com.petshop.pet_service.core.port.in.dto.BookingSearchCriteriaDto;
import com.petshop.pet_service.core.port.in.dto.BookingUpdateDto;

import java.util.List;
import java.util.UUID;

public interface BookingPortIn {
    PetBooking createBooking(BookingRequestDto request);

    List<PetBooking> findBookings(BookingSearchCriteriaDto criteria);

    PetBooking findBookingById(UUID id);

    PetBooking updateBooking(UUID id, BookingUpdateDto bookingUpdate);

    PetBooking finalizeBooking(UUID id, String employeName, String serviceDetails);
}
