package com.petshop.pet_service.core.application.service;

import com.petshop.pet_service.core.domain.PetBooking;
import com.petshop.pet_service.core.port.in.BookingPortIn;
import com.petshop.pet_service.core.port.in.dto.BookingRequestDto;
import com.petshop.pet_service.core.port.in.dto.BookingSearchCriteriaDto;
import com.petshop.pet_service.core.port.in.dto.BookingUpdateDto;
import com.petshop.pet_service.core.port.out.BookingPortOut;
import com.petshop.pet_service.core.port.out.CustomerManagementPortOut;
import com.petshop.pet_service.core.port.out.NotificationPortOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService implements BookingPortIn {

    private final BookingPortOut bookingPortOut;
    private final CustomerManagementPortOut customerManagementPortOut;
    private final NotificationPortOut notificationPortOut;
    //Agendamento
        // agendamento de multiplos pets para o mesmo cliente, com possibilidade de escolher horários simultâneos (de acordo com a disponibilidade)
        // possibilidade de buscar o pet em casa (serviço de busca e entrega) com taxa adicional de acordo com a distância do cliente (taxa de 10 reais +2,5 reais por km, considerando a distância entre o endereço do cliente e a loja)
        // para garantir que o serviço seja cumprido, o agendamento utilizando serviço de buscar e entrega deve ser feito até as 20h do dia anterior ao agendamento.

    @Override
    @Transactional
    public PetBooking createBooking(BookingRequestDto bookingRequest) {
        if (bookingRequest == null) throw new IllegalArgumentException("Booking request cannot be null");

        validateBookingTime(bookingRequest.getBookingDateTime());

        customerManagementPortOut.getAvailableSlots(bookingRequest.getBookingDateTime());

        var booking = new PetBooking(bookingRequest);
        bookingPortOut.save(booking);
        return booking;
    }

    //Listagem de agendamento
        //Apresentação de detalhes do agendamento, como data, horário, tipo de serviço, nome do pet e nome do cliente
        //Apenas os agendamentos futuros são listados, os agendamentos passados são arquivados
        //Possibilidade de filtrar os agendamentos por data, tipo de serviço ou nome do cliente
        //Listagem do histórico de agendamento (do cliente e do funcionário) podendo escolher filtros (abertos, finalizados, cancelados, por data, por tipo de serviço, por cliente, por funcionário)
    @Override
    public List<PetBooking> findBookings(BookingSearchCriteriaDto criteria){
        return bookingPortOut.findByCriteria(criteria);
    }

    @Override
    public PetBooking findBookingById(UUID id) {
        return bookingPortOut.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));
    }

    @Override
    @Transactional
    public PetBooking updateBooking(UUID id, BookingUpdateDto bookingUpdate) {
        var booking = findBookingById(id);

        if (bookingUpdate.getBookingDateTime() != null) {
            validateBookingTime(bookingUpdate.getBookingDateTime());
        }

        booking.Update(bookingUpdate);
        return bookingPortOut.save(booking);
    }

    @Override
    @Transactional
    public PetBooking finalizeBooking(UUID id, String employeName) {
        var booking = findBookingById(id);
        booking.completeBooking(employeName);
        return bookingPortOut.save(booking);
    }


    //Finalização de serviço
        //Listagem de serviços finalizados
        //Foto do antes e depois do serviço
        //Listagem dos produtos utilizados no serviço
        //Notificação para o cliente

    //Cancelamento de agendamento
        //Até 2h antes do horário agendado, o cliente pode cancelar o serviço sem custo. Após esse período, será cobrada uma taxa de cancelamento.

    private void validateBookingTime(LocalDateTime bookingDateTime) {
        if (bookingDateTime == null) throw new IllegalArgumentException("Booking date and time cannot be null");

        var hour = bookingDateTime.getHour();
        var dayOfWeek = bookingDateTime.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("Bookings on Saturdays must be between 8 AM and 11 AM");
        }

        if (dayOfWeek != DayOfWeek.SATURDAY) {
            if (hour < 9 || hour > 17 || (hour >= 12 && hour < 13)) {
                throw new IllegalArgumentException("Bookings on weekdays must be between 9 AM and 5 PM, with a break from 12 PM to 1 PM");
            }
        } else {
            if (hour < 8 || hour > 11) {
                throw new IllegalArgumentException("Bookings on Saturdays must be between 8 AM and 11 AM");
            }
        }

        var now = LocalDateTime.now();
        var twoHoursFromNow = now.plusHours(2);

        if (!bookingDateTime.isAfter(twoHoursFromNow)) {
            throw new IllegalArgumentException("Booking must be made at least 2 hours in advance");
        }
    }
}
