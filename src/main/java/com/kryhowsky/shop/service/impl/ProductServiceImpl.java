package com.kryhowsky.shop.service.impl;

import com.amazonaws.services.s3.AmazonS3;
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

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
        Path path = Paths.get(fileHelper.generatePath(image, product));
//        Files.copy(image.getInputStream(), path);
//        fileHelper.copyInputStream().accept(image.getInputStream(), path);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.addUserMetadata("Content-Type", image.getContentType());
        objectMetadata.addUserMetadata("Content-Length", String.valueOf(image.getSize()));

        amazonS3.putObject(filePropertiesConfig.getS3BucketName(), "product_" + product.getId() + "." + FilenameUtils.getExtension(image.getOriginalFilename()), image.getInputStream(), objectMetadata);
        product.setImagePath(path.toString());
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
            Files.delete(Path.of(productDb.getImagePath()));
        }

        if (image != null) {
            Path path = Paths.get(filePropertiesConfig.getProduct(), "product_" + productDb.getId() + "." + FilenameUtils.getExtension(image.getOriginalFilename()));
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            productDb.setImagePath(path.toString());
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
}
