package com.kryhowsky.shop.flyweight.standard.strategy.impl;

import com.kryhowsky.shop.flyweight.model.FileType;
import com.kryhowsky.shop.flyweight.standard.strategy.GeneratorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonGenerator implements GeneratorStrategy {

    @Override
    public void generateFile() {
        log.info("Generating CSV file...");
    }

    @Override
    public FileType getType() {
        return FileType.JSON;
    }
}
