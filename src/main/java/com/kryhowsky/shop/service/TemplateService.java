package com.kryhowsky.shop.service;

import com.kryhowsky.shop.model.dao.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TemplateService {

    Template findByName(String name);
    Template save(Template template);
    Page<Template> getPage(Pageable pageable);
    void delete(Long id);

}
