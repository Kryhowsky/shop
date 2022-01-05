package com.kryhowsky.shop.mapper;

import com.kryhowsky.shop.model.dao.Product;
import com.kryhowsky.shop.model.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends AuditableMapper<Product, ProductDto> {

    ProductDto toDto(Product product);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Product toDao(ProductDto productDto);

}
