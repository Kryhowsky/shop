package com.kryhowsky.shop.flyweight.generic.strategy.file.impl;

import com.kryhowsky.shop.flyweight.generic.strategy.file.FileGeneratorStrategy;
import com.kryhowsky.shop.flyweight.model.FileType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonFileGenerator implements FileGeneratorStrategy {

    @Override
    public FileType getType() {
        return FileType.JSON;
    }

    @Override
    public byte[] generateFile() {
        log.info("Generating JSON file using Generic Strategy...");
        return new byte[0];
    }
}
