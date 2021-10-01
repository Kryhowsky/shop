//package com.kryhowsky.shop.mapper.impl;
//
//import com.kryhowsky.shop.mapper.UserMapper;
//import com.kryhowsky.shop.model.dao.User;
//import com.kryhowsky.shop.model.dto.UserDto;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserMapperImpl implements UserMapper {
//
//    @Override
//    public UserDto toDto(User user) {
//        return UserDto.builder()
//                .id(user.getId())
//                .email(user.getEmail())
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .build();
//    }
//
//    @Override
//    public User toDao(UserDto userDto) {
//        return User.builder()
//                .id(userDto.getId())
//                .email(userDto.getEmail())
//                .firstName(userDto.getFirstName())
//                .lastName(userDto.getLastName())
//                .password(userDto.getPassword())
//                .build();
//    }
//}
