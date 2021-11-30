package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.mapper.ProductMapper;
import com.kryhowsky.shop.model.dto.ProductDto;
import com.kryhowsky.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    public ProductDto saveProduct(@RequestPart @Valid ProductDto product, @RequestPart MultipartFile image) {
        System.out.println(image.getOriginalFilename());
        return productMapper.toDto(productService.save(productMapper.toDao(product), image));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productMapper.toDto(productService.getProductById(id));
    }

    @GetMapping
    public Page<ProductDto> getProductPage(@RequestParam int page,@RequestParam int size) {
        return productService.getPage(PageRequest.of(page, size)).map(productMapper::toDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ProductDto updateProduct(@RequestPart @Valid ProductDto product, @RequestPart(required = false) MultipartFile image, @PathVariable Long id) {
        return productMapper.toDto(productService.update(productMapper.toDao(product), image, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProductById(@PathVariable Long id) {
        productService.delete(id);
    }
}
