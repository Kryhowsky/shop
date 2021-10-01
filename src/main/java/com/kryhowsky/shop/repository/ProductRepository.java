package com.kryhowsky.shop.repository;

import com.kryhowsky.shop.model.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
