package com.kryhowsky.shop.service.impl;

import com.kryhowsky.shop.model.dao.Basket;
import com.kryhowsky.shop.model.dao.Product;
import com.kryhowsky.shop.repository.BasketRepository;
import com.kryhowsky.shop.service.BasketService;
import com.kryhowsky.shop.service.ProductService;
import com.kryhowsky.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public Basket addProduct(Long productId, Integer productQuantity) {// sprawdzanie dostępności ilości produktów
        var currentUser = userService.getCurrentUser();
        var product = productService.getProductById(productId);

        if (product.getQuantity() < productQuantity) {
            throw new IllegalArgumentException("Wrong product quantity.");
        }

        var basket = new Basket();
        basket.setUser(currentUser);
        basket.setProduct(product);
        basket.setQuantity(productQuantity);

        return basketRepository.save(basket);

    }

    @Override
    public void deleteProduct(Long productId) {
        var currentUser = userService.getCurrentUser();

        basketRepository.deleteByUserIdAndProductId(currentUser.getId(), productId);
    }

    @Override
    @Transactional
    public void updateProductQuantity(Long productId, Integer productQuantity) {

        var currentUser = userService.getCurrentUser();
        var basketToUpdate = basketRepository.findByUserIdAndProductId(currentUser.getId(), productId)
                .orElseGet(() -> addProduct(productId, productQuantity));

        var product = productService.getProductById(productId);

        if (product.getQuantity() > basketToUpdate.getQuantity() + productQuantity) {
            basketToUpdate.setQuantity(basketToUpdate.getQuantity() + productQuantity);
        }
    }

    @Override
    public List<Product> getProducts() {
        var currentUser = userService.getCurrentUser();

        return basketRepository.findByUserId(currentUser.getId()).stream()
                .map(basket -> {
                    var product = basket.getProduct();
                    product.setQuantity(basket.getQuantity());
                    return product;
                })
                .toList();
    }

    @Override
    public void deleteProducts() {
        var currentUser = userService.getCurrentUser();

        basketRepository.deleteByUserId(currentUser.getId());
    }
}
