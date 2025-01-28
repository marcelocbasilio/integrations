package com.marcelocbasilio.integrations.config;

import com.marcelocbasilio.integrations.services.IEmailService;
import com.marcelocbasilio.integrations.services.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public IEmailService emailService() {
        return new MockEmailService();
    }
}
