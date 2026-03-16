package com.petShop.NotificationService.infrastructure.messaging.notification.config.sendgrid;

import com.petShop.NotificationService.core.domain.EmailMessage;
import com.petShop.NotificationService.core.port.out.EmailSenderPortOut;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendGridEmailAdapterOut implements EmailSenderPortOut {

    @Value("${sendgrid.api-key}")
    private String sendGridApiKey;

    @Value("${sendgrid.from-email}")
    private String fromEmailAddress;

    @Override
    public void sendEmail(EmailMessage emailMessage) {
        Email from = new Email(fromEmailAddress);
        String subject = emailMessage.getSubject();
        Email to = new Email(emailMessage.getRecipient());
        Content content = new Content("text/plain", emailMessage.getBody());

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                log.info("SendGrid email successfully sent to: {} | Status: {}", emailMessage.getRecipient(), response.getStatusCode());
            } else {
                log.error("SendGrid API error: {} - {}", response.getStatusCode(), response.getBody());
                throw new RuntimeException("Failed to send email via SendGrid API");
            }

        } catch (IOException ex) {
            log.error("Network error when contacting SendGrid for recipient: {}", emailMessage.getRecipient(), ex);
            // Lançar a exceção garante que o Kafka tente novamente (Retry)
            throw new RuntimeException("Network error contacting SendGrid", ex);
        }
    }
}
