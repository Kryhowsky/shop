package com.kryhowsky.shop.flyweight.generic.strategy.email.impl;

import com.kryhowsky.shop.flyweight.generic.strategy.email.EmailSenderStrategy;
import com.kryhowsky.shop.flyweight.model.EmailType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegistrationEmailSender implements EmailSenderStrategy {

    @Override
    public EmailType getType() {
        return EmailType.REGISTRATION;
    }

    @Override
    public void generateEmail() {
        log.info("Generating Registration Email...");
    }
}
