package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.mapper.ProductMapper;
import com.kryhowsky.shop.model.dto.ProductDto;
import com.kryhowsky.shop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Allows to add new Product.", security = @SecurityRequirement(name = "bearer-key"))
    public ProductDto saveProduct(@RequestPart @Valid ProductDto product, @RequestPart MultipartFile image) {
        return productMapper.toDto(productService.save(productMapper.toDao(product), image));
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns Product with specific ID.")
    public ProductDto getProductById(@PathVariable Long id) {
        return productMapper.toDto(productService.getProductById(id));
    }

    @GetMapping
    @Operation(description = "Returns page of Products with specific size.")
    public Page<ProductDto> getProductPage(@RequestParam int page,@RequestParam int size) {
        return productService.getPage(PageRequest.of(page, size)).map(productMapper::toDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Allows to update the Product with specific ID.")
    public ProductDto updateProduct(@RequestPart @Valid ProductDto product, @RequestPart(required = false) MultipartFile image, @PathVariable Long id) {
        return productMapper.toDto(productService.update(productMapper.toDao(product), image, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Allows to delete Product with specific ID.", security = @SecurityRequirement(name = "bearer-key"))
    public void deleteProductById(@PathVariable Long id) {
        productService.delete(id);
    }
}
