package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.model.dao.Template;
import com.kryhowsky.shop.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping
    @Operation(description = "Allows to add new Template.")
    public Template saveTemplate(@RequestBody Template template) {
        return templateService.save(template);
    }

    @GetMapping("/{name}")
    @Operation(description = "Allows to read Template by ID.")
    public Template getTemplateByName(@PathVariable String name) {
        return templateService.findByName(name);
    }

    @GetMapping
    @Operation(description = "Allows to read page of Templates with specific size.")
    public Page<Template> getPageOfTemplates(@RequestParam int page, @RequestParam int size) {
        return templateService.getPage(PageRequest.of(page, size));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Allows to delete Template with specific ID.")
    public void deleteTemplateById(@PathVariable Long id) {
        templateService.delete(id);
    }

}
