package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.flyweight.generic.GenericFactory;
import com.kryhowsky.shop.flyweight.generic.strategy.email.EmailSenderStrategy;
import com.kryhowsky.shop.flyweight.model.EmailType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/emails")
public class EmailController {

    private final GenericFactory<EmailType, EmailSenderStrategy> genericFactory;

    @GetMapping
    public void sendEmail(@RequestParam EmailType emailType) {
        genericFactory.getStrategyByType(emailType).generateEmail();
    }

}