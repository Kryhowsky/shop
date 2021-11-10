package com.kryhowsky.shop.service;

import com.kryhowsky.shop.model.dao.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    @CachePut(cacheNames = "products", key = "#result.id", unless = "#result.quantity <= 0")
    Product save(Product product);

    @CachePut(cacheNames = "products", key = "#id", unless = "#result.quantity <= 0")
    Product update(Product product, Long id);

    @CacheEvict(cacheNames = "products", key = "#id")
    void delete(Long id);

    @Cacheable(cacheNames = "products", key = "#id")
    Product getProductById(Long id);

    Page<Product> getPage(Pageable pageable);

}
