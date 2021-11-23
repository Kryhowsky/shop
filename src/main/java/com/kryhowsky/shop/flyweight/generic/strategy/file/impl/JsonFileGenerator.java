package com.kryhowsky.shop.flyweight.generic.strategy.file.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kryhowsky.shop.flyweight.generic.strategy.file.FileGeneratorStrategy;
import com.kryhowsky.shop.flyweight.model.FileType;
import com.kryhowsky.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonFileGenerator implements FileGeneratorStrategy {

    private final ObjectMapper objectMapper;
    private final ProductRepository productRepository;

    @Override
    public FileType getType() {
        return FileType.JSON;
    }

    @SneakyThrows
    @Override
    public byte[] generateFile() {
        log.info("Generating JSON file using Generic Strategy...");
        return objectMapper.writeValueAsBytes(productRepository.findAll());
    }
}
