package com.marcelocbasilio.integrations.services;

import com.marcelocbasilio.integrations.dto.EmailDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockEmailService implements IEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    public void sendEmail(EmailDto emailDto) {
        LOG.info("Sending email to: {}", emailDto.getTo());
        LOG.info("Email sent!");
    }
}
