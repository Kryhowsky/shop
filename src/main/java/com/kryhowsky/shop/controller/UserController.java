package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.mapper.UserMapper;
import com.kryhowsky.shop.model.dto.UserDto;
import com.kryhowsky.shop.service.UserService;
import com.kryhowsky.shop.validator.group.Create;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController { // warstwa do komunikacji z klientem

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping
    @Validated(Create.class)
    public UserDto saveUser(@RequestBody @Valid UserDto user) {
        return userMapper.toDto(userService.save(userMapper.toDao(user)));
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.toDto(userService.getUserById(id));
    }

    @GetMapping
    public Page<UserDto> getUserPage(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size)).map(userMapper::toDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody @Valid UserDto user, @PathVariable Long id) {
        return userMapper.toDto(userService.update(userMapper.toDao(user), id));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.delete(id);
    }

}
