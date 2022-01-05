package com.kryhowsky.shop.service.impl;

import com.kryhowsky.shop.model.dao.Template;
import com.kryhowsky.shop.repository.TemplateRepository;
import com.kryhowsky.shop.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;

    @Override
    public Template findByName(String name) {
        return templateRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException(name));
    }

    @Override
    public Template save(Template template) {
        return templateRepository.save(template);
    }

    @Override
    public Page<Template> getPage(Pageable pageable) {
        return templateRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        templateRepository.deleteById(id);
    }
}
