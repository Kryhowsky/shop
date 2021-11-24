package com.kryhowsky.shop.mapper;

import com.kryhowsky.shop.model.dao.Product;
import com.kryhowsky.shop.model.dao.User;
import com.kryhowsky.shop.model.dto.ProductDto;
import com.kryhowsky.shop.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.history.Revision;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.email", target = "email")
    @Mapping(source = "entity.firstName", target = "firstName")
    @Mapping(source = "entity.lastName", target = "lastName")
    @Mapping(source = "requiredRevisionNumber", target = "revisionNumber")
    UserDto toUserDto(Revision<Integer, User> revision);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.brand", target = "brand")
    @Mapping(source = "entity.name", target = "name")
    @Mapping(source = "entity.description", target = "description")
    @Mapping(source = "entity.price", target = "price")
    @Mapping(source = "entity.quantity", target = "quantity")
    @Mapping(source = "requiredRevisionNumber", target = "revisionNumber")
    @Mapping(source = "entity.imagePath", target = "imagePath")
    ProductDto toProductDto(Revision<Integer, Product> revision);

}
