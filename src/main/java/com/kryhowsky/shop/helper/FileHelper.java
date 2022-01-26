package com.kryhowsky.shop.helper;

import com.kryhowsky.shop.interfaces.BiConsumerThrowable;
import com.kryhowsky.shop.model.dao.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface FileHelper {

    BiConsumerThrowable<InputStream, Path, IOException> copyInputStream();
    String generatePath(MultipartFile image, Product product);

}
