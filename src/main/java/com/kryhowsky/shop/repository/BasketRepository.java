package com.kryhowsky.shop.repository;

import com.kryhowsky.shop.model.dao.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
