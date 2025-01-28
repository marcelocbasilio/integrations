package com.marcelocbasilio.integrations.services;

import com.marcelocbasilio.integrations.dto.EmailDto;
import com.marcelocbasilio.integrations.services.exceptions.EmailException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    private final SendGrid sendGrid;

    public EmailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public void sendEmail(EmailDto emailDto) {
        Email from = new Email(emailDto.getFromEmail(), emailDto.getFromName());
        Email to = new Email(emailDto.getTo());
        Content content = new Content(emailDto.getContentType(), emailDto.getBody());
        Mail mail = new Mail(from, emailDto.getSubject(), to, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            LOG.info("Sending email to: {}", emailDto.getTo());
            Response response = sendGrid.api(request);
            if (response.getStatusCode() >= 400) {
                LOG.error("Error sending email to: {}", emailDto.getTo());
                LOG.error("Response code: {}", response.getStatusCode());
                LOG.error("Response body: {}", response.getBody());
                throw new EmailException(response.getBody());
            }
            LOG.info("Email sent successfully to: {}", emailDto.getTo());
            LOG.info("Response code: {}", response.getStatusCode());
            LOG.info("Response body: {}", response.getBody());
        } catch (IOException e) {
            LOG.error("Error sending email to: {}", e.getMessage());
            throw new EmailException(e.getMessage());
        }
    }
}
