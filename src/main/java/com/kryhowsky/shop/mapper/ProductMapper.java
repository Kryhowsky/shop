package com.kryhowsky.shop.mapper;

import com.kryhowsky.shop.model.dao.Product;
import com.kryhowsky.shop.model.dto.ProductDto;

public interface ProductMapper {

    ProductDto toDto(Product product);
    Product toDao(ProductDto productDto);

}
