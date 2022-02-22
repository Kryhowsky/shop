package com.kryhowsky.shop.mapper;

import com.kryhowsky.shop.model.dao.Role;
import com.kryhowsky.shop.model.dao.User;
import com.kryhowsky.shop.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper extends AuditableMapper<User, UserDto> {

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "roles", target = "roles", qualifiedByName = "roleNamesMapper")
    UserDto toDto(User user);

    @Mapping(target = "roles", ignore = true)
    User toDao(UserDto userDto);

    @Named("roleNamesMapper")
    default List<String> roleNamesMapper(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

}
