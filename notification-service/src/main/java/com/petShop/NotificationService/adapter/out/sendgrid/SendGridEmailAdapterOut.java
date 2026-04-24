package com.petShop.NotificationService.adapter.out.sendgrid;

import com.petShop.NotificationService.core.application.port.out.EmailSenderPortOut;
import com.petShop.NotificationService.core.domain.EmailNotification;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendGridEmailAdapterOut implements EmailSenderPortOut {

    private final SendGrid sendGrid;

    @Value("${sendgrid.from-email}")
    private String fromEmail;

    @Override
    public void send(EmailNotification emailNotification) {
        Email from = new Email(fromEmail);
        Email to = new Email(emailNotification.recipient());
        Content content = new Content("text/html", emailNotification.content());
        Mail mail = new Mail(from, emailNotification.subject(), to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            log.info("Email sent to [{}]. SendGrid Status Code: {}",
                    emailNotification.recipient(), response.getStatusCode());

        } catch (IOException ex) {
            log.error("Critical Error: Failed to send email via SendGrid to [{}]",
                    emailNotification.recipient(), ex);
            throw new RuntimeException("SendGrid API Integration Error", ex);
        }
    }
}