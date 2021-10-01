package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.mapper.ProductMapper;
import com.kryhowsky.shop.model.dto.ProductDto;
import com.kryhowsky.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto product) { // klient komunikuje się za pomocą DTO
        return productMapper.toDto(productService.save(productMapper.toDao(product)));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productMapper.toDto(productService.getProductById(id));
    }

    @GetMapping
    public Page<ProductDto> getProductPage(@RequestParam int page,@RequestParam int size) {
        return productService.getPage(PageRequest.of(page, size)).map(productMapper::toDto);
    }
}
