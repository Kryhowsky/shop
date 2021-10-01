//package com.kryhowsky.shop.mapper.impl;
//
//import com.kryhowsky.shop.mapper.ProductMapper;
//import com.kryhowsky.shop.model.dao.Product;
//import com.kryhowsky.shop.model.dto.ProductDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ProductMapperImpl implements ProductMapper {
//
//    @Override
//    public ProductDto toDto(Product product) {
//        return ProductDto.builder()
//                .id(product.getId())
//                .brand(product.getBrand())
//                .name(product.getName())
//                .description(product.getDescription())
//                .price(product.getPrice())
//                .quantity(product.getQuantity())
//                .build();
//    }
//
//    @Override
//    public Product toDao(ProductDto productDto) {
//        return Product.builder()
//                .id(productDto.getId())
//                .brand(productDto.getBrand())
//                .name(productDto.getName())
//                .description(productDto.getDescription())
//                .price(productDto.getPrice())
//                .quantity(productDto.getQuantity())
//                .build();
//    }
//}
