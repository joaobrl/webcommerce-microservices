package com.petshop.pet_service.api.rest;

import com.petshop.pet_service.core.port.in.dto.BookingRequestDto;
import com.petshop.pet_service.core.port.in.dto.BookingSearchCriteriaDto;
import com.petshop.pet_service.core.port.in.dto.BookingUpdateDto;
import com.petshop.pet_service.core.port.out.dto.BookingResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

public interface BookingController {

    ResponseEntity createBooking(@Valid @RequestBody BookingRequestDto bookingRequest, UriComponentsBuilder uriBuilder);

    ResponseEntity<List<BookingResponseDto>> listBookings(BookingSearchCriteriaDto criteria);

    ResponseEntity getBookingById(@PathVariable UUID id);

    ResponseEntity updateBooking(@PathVariable UUID id, @Valid @RequestBody BookingUpdateDto bookingUpdate);

    ResponseEntity finalizeBooking(@PathVariable UUID id, @RequestParam(value = "EmployeName") String EmployeName, @RequestBody String serviceDetails);

}
