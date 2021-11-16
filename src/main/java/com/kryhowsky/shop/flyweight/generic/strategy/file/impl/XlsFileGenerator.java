package com.kryhowsky.shop.flyweight.generic.strategy.file.impl;

import com.kryhowsky.shop.flyweight.generic.strategy.file.FileGeneratorStrategy;
import com.kryhowsky.shop.flyweight.model.FileType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class XlsFileGenerator implements FileGeneratorStrategy {

    @Override
    public FileType getType() {
        return FileType.XLS;
    }

    @Override
    public byte[] generateFile() {
        log.info("Generating XLS file using Generic Strategy..");
        return new byte[0];
    }
}
