package com.marcelocbasilio.integrations.services;

import com.marcelocbasilio.integrations.dto.EmailDto;

public interface IEmailService {
    void sendEmail(EmailDto emailDto);
}
