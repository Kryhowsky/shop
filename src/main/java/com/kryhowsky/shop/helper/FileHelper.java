package com.kryhowsky.shop.helper;

import com.kryhowsky.shop.interfaces.BiConsumerThrowable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface FileHelper {

    BiConsumerThrowable<InputStream, Path, IOException> copyInputStream();

}
