package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.mapper.TemplateMapper;
import com.kryhowsky.shop.model.dto.TemplateDto;
import com.kryhowsky.shop.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/templates", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;
    private final TemplateMapper templateMapper;

    @PostMapping
    @Operation(description = "Allows to add new Template.")
    public TemplateDto saveTemplate(@RequestBody TemplateDto templateDto) {
        return templateMapper.toDto(templateService.save(templateMapper.toDao(templateDto)));
    }

    @GetMapping("/{name}")
    @Operation(description = "Allows to read Template by ID.")
    public TemplateDto getTemplateByName(@PathVariable String name) {
        return templateMapper.toDto(templateService.findByName(name));
    }

    @GetMapping
    @Operation(description = "Allows to read page of Templates with specific size.")
    public Page<TemplateDto> getTemplatePage(@RequestParam int page, @RequestParam int size) {
        return templateService.getPage(PageRequest.of(page, size)).map(templateMapper::toDto);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Allows to delete Template with specific ID.")
    public void deleteTemplateById(@PathVariable Long id) {
        templateService.delete(id);
    }

}
