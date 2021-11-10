package com.kryhowsky.shop.flyweight.standard.strategy.Impl;

import com.kryhowsky.shop.flyweight.model.FileType;
import com.kryhowsky.shop.flyweight.standard.strategy.GeneratorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class XlsGenerator implements GeneratorStrategy {

    @Override
    public void generateFile() {
        log.info("Generating XLS file...");
    }

    @Override
    public FileType getType() {
        return FileType.XLS;
    }
}
