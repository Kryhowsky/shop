package com.kryhowsky.shop.service.impl;

import com.kryhowsky.shop.config.properties.FilePropertiesConfig;
import com.kryhowsky.shop.model.dao.Product;
import com.kryhowsky.shop.repository.ProductRepository;
import com.kryhowsky.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FilePropertiesConfig filePropertiesConfig;

    @SneakyThrows
    @Override
    public Product save(Product product, MultipartFile image) {
        productRepository.save(product);
        String[] splittedFileName = image.getOriginalFilename().split("\\.");
        Path path = Paths.get(filePropertiesConfig.getProduct(), "product_" + product.getId() + "." + splittedFileName[splittedFileName.length - 1]);
        Files.copy(image.getInputStream(), path);
        product.setImagePath(path.toString());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Product product, Long id) {

        var productDb = getProductById(id);
        productDb.setBrand(product.getBrand());
        productDb.setName(product.getName());
        productDb.setDescription(product.getDescription());
        productDb.setPrice(product.getPrice());
        productDb.setQuantity(product.getQuantity());

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
