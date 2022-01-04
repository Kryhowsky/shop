package com.kryhowsky.shop.mapper;

import com.kryhowsky.shop.model.dao.User;
import com.kryhowsky.shop.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends AuditableMapper<User, UserDto>{

    @Mapping(target = "password", ignore = true)
    UserDto toDto(User user);
    User toDao(UserDto userDto);

}
