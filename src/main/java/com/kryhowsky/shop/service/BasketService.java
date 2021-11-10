package com.kryhowsky.shop.service;

import com.kryhowsky.shop.model.dao.Basket;
import com.kryhowsky.shop.model.dao.Product;

import java.util.List;

public interface BasketService {

    Basket addProduct(Long productId, Integer productQuantity);
    void deleteProduct(Long productId);
    void updateProductQuantity(Long productId, Integer productQuantity);
    List<Product> getProducts();
    void deleteProducts();

}
