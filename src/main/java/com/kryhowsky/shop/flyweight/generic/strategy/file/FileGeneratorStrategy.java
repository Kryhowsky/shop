package com.kryhowsky.shop.flyweight.generic.strategy.file;

import com.kryhowsky.shop.flyweight.generic.strategy.GenericStrategy;
import com.kryhowsky.shop.flyweight.model.FileType;

public interface FileGeneratorStrategy extends GenericStrategy<FileType> {

    byte[] generateFile();

}
