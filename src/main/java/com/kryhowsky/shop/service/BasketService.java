package com.kryhowsky.shop.service;

public interface BasketService {

    void addProduct(Long productId, Integer productQuantity);
    void deleteProduct(Long productId);
    void updateProductQuantity(Long productId, Integer productQuantity);
    void getProducts();
    void deleteProducts();

}
