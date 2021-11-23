package com.kryhowsky.shop.flyweight.generic.strategy.email.impl;

import com.kryhowsky.shop.flyweight.generic.strategy.email.EmailGeneratorStrategy;
import com.kryhowsky.shop.flyweight.model.EmailType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegistrationEmailGenerator implements EmailGeneratorStrategy {

    @Override
    public EmailType getType() {
        return EmailType.REGISTRATION;
    }

    @Override
    public byte[] generateEmail() {
        log.info("Generating Registration Email...");
        return new byte[0];
    }
}
