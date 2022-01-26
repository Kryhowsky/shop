package com.kryhowsky.shop.helper.impl;

import com.kryhowsky.shop.config.properties.FilePropertiesConfig;
import com.kryhowsky.shop.helper.FileHelper;
import com.kryhowsky.shop.interfaces.BiConsumerThrowable;
import com.kryhowsky.shop.model.dao.Product;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public class FileHelperImpl implements FileHelper {

    private final FilePropertiesConfig filePropertiesConfig;

    @Override
    public BiConsumerThrowable<InputStream, Path, IOException> copyInputStream() {
        return Files::copy;
    }

    @Override
    public String generatePath(MultipartFile image, Product product) {
        return filePropertiesConfig.getS3Endpoint() + "/" +
                filePropertiesConfig.getS3BucketName() + "/" +
                "product_" + product.getId() + "." + FilenameUtils.getExtension(image.getOriginalFilename());
    }

}
