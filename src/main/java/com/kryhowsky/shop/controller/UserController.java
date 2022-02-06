package com.kryhowsky.shop.controller;

import com.kryhowsky.shop.mapper.UserMapper;
import com.kryhowsky.shop.model.dto.UserDto;
import com.kryhowsky.shop.service.UserService;
import com.kryhowsky.shop.validator.group.Create;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController { // warstwa do komunikacji z klientem

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping
    @Validated(Create.class)
    @Operation(description = "Allows to add new User.")
    public UserDto saveUser(@RequestBody @Valid UserDto user) {
        return userMapper.toDto(userService.save(userMapper.toDao(user)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns user by given ID", security = @SecurityRequirement(name = "bearer-key"))
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.toDto(userService.getUserById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Returns page of Users with specific size.", security = @SecurityRequirement(name = "bearer-key"))
    public Page<UserDto> getUserPage(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size)).map(userMapper::toDto);
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update user specified by ID.")
    public UserDto updateUser(@RequestBody @Valid UserDto user, @PathVariable Long id) {
        return userMapper.toDto(userService.update(userMapper.toDao(user), id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Allows to delete user specified by ID.", security = @SecurityRequirement(name = "bearer-key"))
    public void deleteUserById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/activate")
    @Operation(description = "Allows to activate the user.")
    public void activateUserByActivationToken(@RequestParam String token) {
        userService.activateUser(token);
    }

    @GetMapping("/current")
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Allows to check information about logged user.", security = @SecurityRequirement(name = "bearer-key"))
    public UserDto getCurrentUser() {
        return userMapper.toDto(userService.getCurrentUser());
    }

}
