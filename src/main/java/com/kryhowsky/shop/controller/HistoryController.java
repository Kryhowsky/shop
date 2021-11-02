package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.mapper.HistoryMapper;
import com.kryhowsky.shop.model.dto.ProductDto;
import com.kryhowsky.shop.model.dto.UserDto;
import com.kryhowsky.shop.repository.ProductRepository;
import com.kryhowsky.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final HistoryMapper historyMapper;

    @GetMapping("/users/{userId}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#userId))")
    public Page<UserDto> getUserHistory(@PathVariable Long userId, @RequestParam int page, @RequestParam int size) {
        return userRepository.findRevisions(userId, PageRequest.of(page, size)).map(historyMapper::toUserDto);
    }

    @GetMapping("/products/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<ProductDto> getProductHistory(@PathVariable Long productId, @RequestParam int page, @RequestParam int size) {
        return productRepository.findRevisions(productId, PageRequest.of(page, size)).map(historyMapper::toProductDto);
    }
}
