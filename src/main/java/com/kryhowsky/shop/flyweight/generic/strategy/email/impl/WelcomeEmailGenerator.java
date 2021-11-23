package com.kryhowsky.shop.flyweight.generic.strategy.email.impl;

import com.kryhowsky.shop.flyweight.generic.strategy.email.EmailGeneratorStrategy;
import com.kryhowsky.shop.flyweight.model.EmailType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WelcomeEmailGenerator implements EmailGeneratorStrategy {

    @Override
    public EmailType getType() {
        return EmailType.WELCOME;
    }

    @Override
    public byte[] generateEmail() {
        log.info("Generating Welcome Email...");
        return new byte[0];
    }
}
