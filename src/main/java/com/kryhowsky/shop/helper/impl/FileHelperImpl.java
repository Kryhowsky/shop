package com.kryhowsky.shop.helper.impl;

import com.kryhowsky.shop.helper.FileHelper;
import com.kryhowsky.shop.interfaces.BiConsumerThrowable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileHelperImpl implements FileHelper {

    @Override
    public BiConsumerThrowable<InputStream, Path, IOException> copyInputStream() {
        return Files::copy;
    }

}
