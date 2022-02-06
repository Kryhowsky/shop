package com.kryhowsky.shop.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.kryhowsky.shop.config.properties.FilePropertiesConfig;
import com.kryhowsky.shop.helper.FileHelper;
import com.kryhowsky.shop.model.dao.Product;
import com.kryhowsky.shop.repository.ProductRepository;
import com.kryhowsky.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FilePropertiesConfig filePropertiesConfig;
    private final FileHelper fileHelper;
    private final AmazonS3 amazonS3;

    @SneakyThrows
    @Override
    public Product save(Product product, MultipartFile image) {

        productRepository.save(product);
        saveImageForProduct(product, image);

        return productRepository.save(product);
    }

    @SneakyThrows
    @Override
    @Transactional
    public Product update(Product product, MultipartFile image, Long id) {

        var productDb = getProductById(id);
        productDb.setBrand(product.getBrand());
        productDb.setName(product.getName());
        productDb.setDescription(product.getDescription());
        productDb.setPrice(product.getPrice());
        productDb.setQuantity(product.getQuantity());

        if (productDb.getImagePath() != null) {
            amazonS3.deleteObject(
                    new DeleteObjectRequest(
                            filePropertiesConfig.getS3BucketName(),
                            FilenameUtils.getName(productDb.getImagePath())
                    )
            );
        }

        if (image != null) {
            saveImageForProduct(product, image);
        }

        return productDb;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(Long id) {
        log.info("Product id {} not in cache", id);
        return productRepository.getById(id);
    }

    private void saveImageForProduct(Product product, MultipartFile image) throws IOException {
        Path path = Paths.get(fileHelper.generatePath(image, product));

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.addUserMetadata("Content-Type", image.getContentType());
        objectMetadata.addUserMetadata("Content-Length", String.valueOf(image.getSize()));

        amazonS3.putObject(
                filePropertiesConfig.getS3BucketName(),
                "product_" + product.getId() + "." + FilenameUtils.getExtension(image.getOriginalFilename()),
                image.getInputStream(),
                objectMetadata
        );

        product.setImagePath(path.toString());
    }
}
