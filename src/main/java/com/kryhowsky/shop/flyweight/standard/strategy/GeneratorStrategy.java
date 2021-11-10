package com.kryhowsky.shop.flyweight.standard.strategy;

import com.kryhowsky.shop.flyweight.model.FileType;

public interface GeneratorStrategy {
    void generateFile();
    FileType getType();
}
