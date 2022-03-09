package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.mapper.ProductMapper;
import com.kryhowsky.shop.model.dto.BasketDto;
import com.kryhowsky.shop.model.dto.ProductDto;
import com.kryhowsky.shop.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService;
    private final ProductMapper productMapper;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Allows to add product to the basket.", security = @SecurityRequirement(name = "bearer-key"))
    public void addProductToBasket(@RequestBody BasketDto basketDto) {
        basketService.addProduct(basketDto.getProductId(), basketDto.getQuantity());
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Allows to get products for the user.", security = @SecurityRequirement(name = "bearer-key"))
    public List<ProductDto> getBasketProducts() {
        return productMapper.toDtoList(basketService.getProducts());
    }
}
