package com.marcelocbasilio.integrations.config;

import com.marcelocbasilio.integrations.services.IEmailService;
import com.marcelocbasilio.integrations.services.SendGridEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public IEmailService emailService() {
        return new SendGridEmailService();
    }
}
