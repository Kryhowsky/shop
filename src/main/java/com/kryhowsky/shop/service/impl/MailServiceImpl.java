package com.kryhowsky.shop.service.impl;

import com.kryhowsky.shop.model.dao.Template;
import com.kryhowsky.shop.service.MailService;
import com.kryhowsky.shop.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final TemplateService templateService;
    private final ITemplateEngine iTemplateEngine;

    @Async
    @Override
    public void sendEmail(Map<String, Object> variables, String templateName, String emailReceiver) {
        Template template = templateService.findByName(templateName);
        Context context = new Context(Locale.getDefault(), variables);
        String mailBody = iTemplateEngine.process(template.getBody(), context);
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(emailReceiver);
            mimeMessageHelper.setSubject(template.getSubject());
            mimeMessageHelper.setText(mailBody, true);
        });
    }

}
