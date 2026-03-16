package com.petShop.NotificationService.core.application.service;


import com.petShop.NotificationService.core.domain.EmailMessage;
import com.petShop.NotificationService.core.port.in.SendBookingConfirmationCommand;
import com.petShop.NotificationService.core.port.in.SendNotificationUseCase;
import com.petShop.NotificationService.core.port.out.EmailSenderPortOut;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements SendNotificationUseCase {

    private final EmailSenderPortOut emailSenderPortOut;

    @Override
    public void sendBookingConfirmation(SendBookingConfirmationCommand command) {
        log.info("Processing booking confirmation for pet: {}", command.petName());

        String subject = "PetShop - Booking Confirmed!";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = command.bookingDateTime().format(formatter);


        String body = String.format("""
                Olá %s,
                
                Temos uma ótima notícia: o serviço do(a) %s foi concluído com sucesso! Ele(a) já está pronto(a) e cheiroso(a) esperando por você.
                
                Detalhes do Atendimento:
                - Serviço Realizado: %s
                - Profissional: %s
                - Data/Hora do Agendamento: %s
                
                P.S.: Em breve, teremos uma novidade incrível! Você passará a receber as fotos do "Antes e Depois" do seu pet diretamente aqui no e-mail. Fique de olho nas próximas visitas!
                
                Agradecemos a confiança,
                Equipe PetShop
                """,
                command.ownerName(),
                command.petName(),
                command.serviceType(),
                formattedDate,
                command.employeeName(),
                command.petName()
        );

        EmailMessage emailMessage = new EmailMessage(command.ownerContact(), subject, body);

        // Good IT Practice: Delegate the actual networking to the output port
        emailSenderPortOut.sendEmail(emailMessage);
    }
}
