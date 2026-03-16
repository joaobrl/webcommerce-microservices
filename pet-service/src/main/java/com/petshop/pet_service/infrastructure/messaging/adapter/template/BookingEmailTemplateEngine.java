package com.petshop.pet_service.infrastructure.messaging.adapter.template;

import com.petshop.pet_service.core.domain.PetBooking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class BookingEmailTemplateEngine {

    private final SpringTemplateEngine templateEngine;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");

    public String buildScheduledEmail(PetBooking booking) {
        Context context = new Context();
        context.setVariable("ownerName", booking.getPet().getOwnerName());
        context.setVariable("petName", booking.getPet().getPetName());
        context.setVariable("serviceType", booking.getServiceDetails().getServiceType().name());
        context.setVariable("bookingDate", booking.getBookingDateTime().format(DATE_FORMATTER));
        context.setVariable("price", booking.getServiceDetails().getPrice());

        return templateEngine.process("booking-scheduled", context);
    }

    public String buildCompletedEmail(PetBooking booking) {
        Context context = new Context();
        context.setVariable("ownerName", booking.getPet().getOwnerName());
        context.setVariable("petName", booking.getPet().getPetName());
        context.setVariable("serviceType", booking.getServiceDetails().getServiceType().name());
        context.setVariable("employeeName", booking.getEmployeeName());

        return templateEngine.process("booking-completed", context);
    }

    public String buildCanceledEmail(PetBooking booking) {
        Context context = new Context();
        context.setVariable("ownerName", booking.getPet().getOwnerName());
        context.setVariable("petName", booking.getPet().getPetName());
        context.setVariable("serviceType", booking.getServiceDetails().getServiceType().name());

        return templateEngine.process("booking-canceled", context);
    }
}