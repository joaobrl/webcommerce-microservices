package com.petshop.pet_service.api.rest;

import com.petshop.pet_service.core.port.in.BookingPortIn;
import com.petshop.pet_service.core.port.in.dto.BookingRequestDto;
import com.petshop.pet_service.core.port.in.dto.BookingSearchCriteriaDto;
import com.petshop.pet_service.core.port.in.dto.BookingUpdateDto;
import com.petshop.pet_service.core.port.out.dto.BookingResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingControllerImpl implements BookingController {

    private final BookingPortIn bookingPortIn;

    @PostMapping("/create")
    public ResponseEntity createBooking(@Valid @RequestBody BookingRequestDto bookingRequest, UriComponentsBuilder uriBuilder) {
        var booking = bookingPortIn.createBooking(bookingRequest);
        var uri = uriBuilder.path("/bookings/{id}").buildAndExpand(booking.getId()).toUri();
        return ResponseEntity.created(uri).body(new BookingResponseDto(booking));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BookingResponseDto>> listBookings(BookingSearchCriteriaDto criteria) {
        var bookings = bookingPortIn.findBookings(criteria)
                .stream()
                .map(BookingResponseDto::new)
                .toList();
        return ResponseEntity.ok(bookings);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity getBookingById(@PathVariable UUID id) {
        var booking = bookingPortIn.findBookingById(id);
        return ResponseEntity.ok(new BookingResponseDto(booking));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity updateBooking(@PathVariable UUID id, @RequestBody BookingUpdateDto bookingUpdate) {
        var booking = bookingPortIn.updateBooking(id, bookingUpdate);
        return ResponseEntity.ok(new BookingResponseDto(booking));
    }

    @Override
    @PutMapping("/{id}/finalize")
    public ResponseEntity finalizeBooking(@PathVariable UUID id, @RequestParam (value = "EmployeName") String EmployeName, @RequestBody String serviceDetails) {
        var booking = bookingPortIn.finalizeBooking(id, EmployeName, serviceDetails);
        return ResponseEntity.ok(new BookingResponseDto(booking));
    }


    //Finalização de serviço
        //Listagem de serviços finalizados
        //Foto do antes e depois do serviço
        //Notificação para o cliente
        //Listagem dos produtos utilizados no serviço

    //Cancelamento de agendamento
}
